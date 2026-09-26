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
    document.getElementById("withdrawForm");

const accountId =
    document.getElementById("accountId");

const amount =
    document.getElementById("amount");

const button =
    document.getElementById("withdrawBtn");

const message =
    document.getElementById("message");

const result =
    document.getElementById("result");


form.addEventListener("submit", async function (event) {

    event.preventDefault();

    const id = accountId.value.trim();
    const withdrawAmount = Number(amount.value);

    if (!id) {
        showMessage("Please enter account ID.", false);
        return;
    }

    if (withdrawAmount <= 0) {
        showMessage(
            "Withdrawal amount must be greater than zero.",
            false
        );
        return;
    }

    button.disabled = true;
    button.textContent = "Processing...";

    try {

        const response = await fetch(
            `/api/accounts/${encodeURIComponent(id)}/withdraw`,
            {
                method: "PUT",

                headers: {
                    "Content-Type": "application/json"
                },

                body: JSON.stringify({
                    balance: withdrawAmount
                })
            }
        );

        const data = await response.json();

        if (!response.ok) {

            throw new Error(
                data.message ||
                "Withdrawal failed."
            );

        }

        document.getElementById("newBalance")
            .textContent =
            "₹ " + Number(data.balance).toFixed(2);

        document.getElementById("holderName")
            .textContent =
            data.accountHolderName || "-";

        result.classList.remove("hidden");

        showMessage(
            "✓ Money withdrawn successfully.",
            true
        );

        amount.value = "";

    } catch (error) {

        result.classList.add("hidden");

        showMessage(
            "⚠ " + error.message,
            false
        );

    } finally {

        button.disabled = false;
        button.innerHTML =
            'Withdraw Money <span>→</span>';

    }

});


function showMessage(text, success) {

    message.textContent = text;

    message.className =
        success ? "success" : "error";

}