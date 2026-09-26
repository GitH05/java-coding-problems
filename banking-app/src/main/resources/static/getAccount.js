/**
 * ============================================================
 * @author      Santosh Kumar Dhawal
 * @role        Software Engineer
 * @created     2026-09-14
 * @version     1.0.0
 * @signature   Original implementation by Santosh Kumar Dhawal
 * ============================================================
 */

const form = document.getElementById("getAccountForm");

const accountIdInput =
    document.getElementById("accountId");

const button =
    document.getElementById("searchBtn");

const message =
    document.getElementById("message");

const result =
    document.getElementById("accountResult");


form.addEventListener("submit", async function (event) {

    event.preventDefault();

    const id = accountIdInput.value.trim();

    if (!id) {
        showMessage("Please enter account ID.", false);
        return;
    }

    button.disabled = true;
    button.innerHTML = "Searching...";

    try {

        const response = await fetch(
            `/api/accounts/${encodeURIComponent(id)}`
        );

        const data = await response.json();

        if (!response.ok) {

            throw new Error(
                data.message || "Account not found."
            );

        }

        document.getElementById("resultId")
            .textContent = data.id ?? "-";

        document.getElementById("resultName")
            .textContent = data.accountHolderName ?? "-";

        document.getElementById("resultBalance")
            .textContent =
            "₹ " + Number(data.balance).toFixed(2);

        result.classList.remove("hidden");

        showMessage(
            "✓ Account found successfully.",
            true
        );

    } catch (error) {

        result.classList.add("hidden");

        showMessage(
            "⚠ " + error.message,
            false
        );

    } finally {

        button.disabled = false;
        button.innerHTML =
            "<span>Find Account</span> <span>→</span>";

    }

});


function showMessage(text, success) {

    message.textContent = text;

    message.className =
        success ? "success" : "error";

}