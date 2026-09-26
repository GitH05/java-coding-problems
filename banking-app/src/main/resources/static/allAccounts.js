/**
 * ============================================================
 * @author      Santosh Kumar Dhawal
 * @role        Software Engineer
 * @created     2026-09-14
 * @version     1.0.0
 * @signature   Original implementation by Santosh Kumar Dhawal
 * ============================================================
 */

const API_BASE = "/api/accounts";

const tableContainer = document.getElementById("tableContainer");
const message = document.getElementById("message");
const loadBtn = document.getElementById("loadBtn");


// Load accounts when page opens
document.addEventListener("DOMContentLoaded", loadAccounts);


// Refresh button
loadBtn.addEventListener("click", loadAccounts);


// =====================================
// LOAD ALL ACCOUNTS
// =====================================

async function loadAccounts() {

    loadBtn.disabled = true;
    loadBtn.textContent = "↻ Loading...";

    message.innerHTML = "";

    try {

        const response = await fetch(API_BASE);

        if (!response.ok) {
            throw new Error("Failed to load accounts.");
        }

        const accounts = await response.json();

        renderAccounts(accounts);

    } catch (error) {

        console.error("Error loading accounts:", error);

        message.innerHTML = `
            <div class="error-message">
                ${escapeHtml(error.message)}
            </div>
        `;

        tableContainer.innerHTML = `
            <div class="empty">

                <div class="empty-icon">
                    !
                </div>

                <h3>Unable to load accounts</h3>

                <p>
                    Please check your backend connection and try again.
                </p>

            </div>
        `;

    } finally {

        loadBtn.disabled = false;
        loadBtn.textContent = "↻ Refresh";
    }
}


// =====================================
// RENDER ACCOUNTS
// =====================================

function renderAccounts(accounts) {

    if (!accounts || accounts.length === 0) {

        tableContainer.innerHTML = `
            <div class="empty">

                <div class="empty-icon">
                    ◎
                </div>

                <h3>No accounts found</h3>

                <p>
                    Create an account to get started.
                </p>

            </div>
        `;

        return;
    }


    let tableHTML = `

        <table class="accounts-table">

            <thead>

                <tr>
                    <th>ID</th>
                    <th>Account Holder</th>
                    <th>Balance</th>
                </tr>

            </thead>

            <tbody>
    `;


    accounts.forEach(account => {

        tableHTML += `

            <tr>

                <td>
                    ${escapeHtml(account.id)}
                </td>

                <td>
                    ${escapeHtml(account.accountHolderName)}
                </td>

                <td class="balance">
                    ₹${Number(account.balance).toFixed(2)}
                </td>

            </tr>
        `;
    });


    tableHTML += `

            </tbody>

        </table>
    `;


    tableContainer.innerHTML = tableHTML;
}


// =====================================
// ESCAPE HTML
// =====================================

function escapeHtml(value) {

    return String(value ?? "")
        .replace(/&/g, "&amp;")
        .replace(/</g, "&lt;")
        .replace(/>/g, "&gt;")
        .replace(/"/g, "&quot;")
        .replace(/'/g, "&#039;");
}