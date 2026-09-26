/**
 * ============================================================
 * @author      Santosh Kumar Dhawal
 * @role        Software Engineer
 * @created     2026-09-14
 * @version     1.0.0
 * @signature   Original implementation by Santosh Kumar Dhawal
 * ============================================================
 */

const form = document.getElementById("createAccountForm");

const nameInput = document.getElementById("accountHolderName");
const balanceInput = document.getElementById("balance");

const button = document.getElementById("createBtn");
const message = document.getElementById("message");

const result = document.getElementById("result");

form.addEventListener("submit", async function (event) {

    event.preventDefault();

    const accountHolderName = nameInput.value.trim();
    const balance = Number(balanceInput.value);

    if (!accountHolderName) {
        showMessage("Please enter account holder name.", false);
        return;
    }

    if (balance < 0 || isNaN(balance)) {
        showMessage("Please enter a valid balance.", false);
        return;
    }

    button.disabled = true;
    button.innerHTML = "Creating account...";

    try {

        const response = await fetch("/api/accounts", {

            method: "POST",

            headers: {
                "Content-Type": "application/json"
            },

            body: JSON.stringify({
                accountHolderName: accountHolderName,
                balance: balance
            })

        });

        const data = await response.json();

        if (!response.ok) {

            throw new Error(
                data.message || "Unable to create account."
            );

        }

        showMessage(
            "✓ Account created successfully.",
            true
        );

        document.getElementById("resultId").textContent =
            data.id ?? "-";

        document.getElementById("resultName").textContent =
            data.accountHolderName ?? "-";

        document.getElementById("resultBalance").textContent =
            "₹ " + Number(data.balance).toFixed(2);

        result.classList.remove("hidden");

        form.reset();

    } catch (error) {

        showMessage(
            "⚠ " + error.message,
            false
        );

    } finally {

        button.disabled = false;

        button.innerHTML =
            "<span>Create Account</span><span class='arrow'>→</span>";

    }

});


function showMessage(text, success) {

    message.textContent = text;

    message.className =
        success ? "success" : "error";

}