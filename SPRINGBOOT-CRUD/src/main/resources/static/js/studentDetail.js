$(document).ready(function() {
    // Initialize jqGrid
    $("#grid").jqGrid({
        url: "get-student-grid",
        datatype: "json",
        mtype: "GET",
        colNames: ['ID', 'Name', 'Address', 'Department', 'Active', 'Actions'],
        colModel: [
            { name: 'id', index: 'id', width: 50, align: 'center', key: true, hidden: true },
            { name: 'name', index: 'name', width: 150 },
            { name: 'address', index: 'address', width: 200 },
            { name: 'department', index: 'department', width: 150 },
            { name: 'active', index: 'active', width: 80, align: 'center' },
            { name: 'actions', index: 'actions', search: false, width: 100, sortable: false, formatter: actionButtons }
        ],
        pager: '#pager',
        rowNum: 10,
        rowList: [10, 20, 30],
        sortname: 'id',
        //sortorder: 'asc',
        viewrecords: true,
        gridview: true,
        jsonReader: {
            root: 'rows',
            page: 'page',
            total: 'total',
            records: 'records',
            repeatitems: false,
            id: "id"
        },
        loadComplete: function(data) {
            console.log(data);
        }
    });
    // ✅ Add Search Toolbar
    $("#grid").jqGrid('filterToolbar', {
        searchOperators: false,   // disable operators dropdown (=, !=, etc.)
        searchOnEnter: false,     // search as you type
        defaultSearch: "cn"       // "cn" = contains
    });

    // ✅ Add Navigation Bar with Refresh Button
    $("#grid").jqGrid('navGrid', '#pager',
        {
            edit: false,
            add: false,
            del: false,
            search: false,
            refresh: true   // enables refresh button
        }
    );


    // Action buttons (Edit/Delete)
    function actionButtons(cellvalue, options, rowObject) {
        return `
            <div class="action-cell">
                <button class="edit-btn" onclick="editStudent('${options.rowId}')">✏️ Edit</button>
                <button class="delete-btn" onclick="deleteStudent('${options.rowId}')">🗑️ Delete</button>
            </div>
        `;
    }

    // Form submission (Add/Update)
    $('#studentForm').submit(function(e) {
        e.preventDefault();
        const name = $('#name').val();
        const address = $('#address').val();
        const department = $('#department').val();

        if (!name || !address || !department) {
            alert("Please fill in all the fields.");
            return;
        }

        const student = {
            id: $('#id').val(),
            name,
            address,
            department
        };

        const url = student.id ? `/update-student-detail/${student.id}` : "/add-student-detail";

        $.ajax({
            url: url,
            method: 'POST',
            contentType: 'application/json',
            data: JSON.stringify(student),
            success: function(response) {
                $('#studentForm')[0].reset();
                $("#grid").trigger('reloadGrid'); // Reload the grid after the action
            },
            error: function(err) {
                console.error(err);
                alert("Error saving student details.");
            }
        });
    });

    // Clear form
    $('#clear').click(function() {
        $('#studentForm')[0].reset();
    });
});

// Edit student details and populate the form
function editStudent(id) {
    $("#studentForm input").focus();
    $("#name").val($("#grid").jqGrid('getCell', id, 'name')).change();
    $("#address").val($("#grid").jqGrid('getCell', id, 'address')).change();
    $("#department").val($("#grid").jqGrid('getCell', id, 'department')).change();
    $("#id").val($("#grid").jqGrid('getCell', id, 'id')).change();
}

// Delete student
function deleteStudent(id) {
    if (confirm("Are you sure you want to delete this student?")) {
        $.ajax({
            url: 'delete-student-detail?id=' + id,
            method: 'DELETE',
            success: function(response) {
                alert("Student deleted successfully.");
                $("#grid").trigger('reloadGrid');  // Reload the grid after deletion
            },
            error: function(err) {
                console.error(err);
                alert("Error deleting student.");
            }
        });
    }
}
