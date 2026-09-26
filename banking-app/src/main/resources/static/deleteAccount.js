/**
 * ============================================================
 * @author      Santosh Kumar Dhawal
 * @role        Software Engineer
 * @created     2026-09-14
 * @version     1.0.0
 * @signature   Original implementation by Santosh Kumar Dhawal
 * ============================================================
 */

const form =
    document.getElementById("deleteForm");

const accountId =
    document.getElementById("accountId");

const button =
    document.getElementById("deleteBtn");

const message =
    document.getElementById("message");


form.addEventListener("submit", async function (event) {

    event.preventDefault();

    const id = accountId.value.trim();

    if (!id) {

        showMessage(
            "Please enter account ID.",
            false
        );

        return;
    }


    const confirmed = confirm(
        `Are you sure you want to delete account #${id}?`
    );

    if (!confirmed) {
        return;
    }


    button.disabled = true;
    button.textContent = "Deleting...";


    try {

        const response = await fetch(
            `/api/accounts/${encodeURIComponent(id)}`,
            {
                method: "DELETE"
            }
        );


        const text =
            await response.text();


        if (!response.ok) {

            let errorMessage =
                "Unable to delete account.";

            try {

                const data =
                    JSON.parse(text);

                errorMessage =
                    data.message || errorMessage;

            } catch {

                if (text) {
                    errorMessage = text;
                }

            }

            throw new Error(errorMessage);
        }


        showMessage(
            "✓ " +
            (text ||
                "Account deleted successfully."),
            true
        );

        accountId.value = "";


    } catch (error) {

        showMessage(
            "⚠ " + error.message,
            false
        );

    } finally {

        button.disabled = false;
        button.textContent =
            "Delete Account";

    }

});


function showMessage(text, success) {

    message.textContent = text;

    message.className =
        success ? "success" : "error";

}