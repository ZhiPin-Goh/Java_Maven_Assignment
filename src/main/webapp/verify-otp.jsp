<%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <!DOCTYPE html>
    <html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Verify OTP - Zen Tea</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    </head>

    <body>
        <% String otpEmail=(String) session.getAttribute("pendingVerificationEmail"); String otpError=(String)
            session.getAttribute("errorMessage"); String otpSuccess=(String) session.getAttribute("successMessage"); if
            (otpError !=null) session.removeAttribute("errorMessage"); if (otpSuccess !=null)
            session.removeAttribute("successMessage"); if (otpEmail==null) otpEmail="your email" ; %>
            <div class="auth-layout">
                <div class="auth-image">
                    <img src="https://images.unsplash.com/photo-1556679343-c7306c1976bc?ixlib=rb-4.0.3&auto=format&fit=crop&w=1000&q=80"
                        alt="Zen Tea">
                    <div class="auth-image-overlay"></div>
                    <div class="auth-image-content">
                        <a href="${pageContext.request.contextPath}/index" class="logo">Zen Tea</a>
                        <h2>Verify Your Account</h2>
                        <p>We've sent a 6-digit verification code to your email. Please enter it below to complete your
                            registration.</p>
                    </div>
                </div>
                <div class="auth-form-side">
                    <div class="auth-form-container">
                        <div class="auth-card">
                            <a href="${pageContext.request.contextPath}/sign-up.jsp"
                                class="text-sm font-medium text-emerald-600 mb-6"
                                style="display:inline-flex;align-items:center;gap:0.25rem;">&larr; Back to sign up</a>
                            <h1 class="font-serif text-3xl font-bold text-center mb-2">Verify OTP</h1>
                            <p class="text-gray-500 text-center mb-8">Please enter the 6-digit code sent to<br><span
                                    class="font-medium text-gray-900">
                                    <%= otpEmail %>
                                </span></p>

                            <% if (otpSuccess !=null) { %>
                                <div class="alert alert-success mb-4">&#10003; <%= otpSuccess %>
                                </div>
                                <% } %>
                                    <% if (otpError !=null) { %>
                                        <div class="alert alert-error mb-4">&#10007; <%= otpError %>
                                        </div>
                                        <% } %>

                                            <form action="${pageContext.request.contextPath}/verifyOtp" method="post"
                                                id="otpForm" onsubmit="return handleVerifySubmit()">
                                                <input type="hidden" name="otp" id="otpHidden">
                                                <div class="otp-inputs mb-8">
                                                    <input type="text" maxlength="1" class="otp-input"
                                                        oninput="otpNext(this,0)" onkeydown="otpBack(event,0)">
                                                    <input type="text" maxlength="1" class="otp-input"
                                                        oninput="otpNext(this,1)" onkeydown="otpBack(event,1)">
                                                    <input type="text" maxlength="1" class="otp-input"
                                                        oninput="otpNext(this,2)" onkeydown="otpBack(event,2)">
                                                    <input type="text" maxlength="1" class="otp-input"
                                                        oninput="otpNext(this,3)" onkeydown="otpBack(event,3)">
                                                    <input type="text" maxlength="1" class="otp-input"
                                                        oninput="otpNext(this,4)" onkeydown="otpBack(event,4)">
                                                    <input type="text" maxlength="1" class="otp-input"
                                                        oninput="otpNext(this,5)" onkeydown="otpBack(event,5)">
                                                </div>
                                                <button type="submit" class="btn btn-emerald btn-block mb-4"
                                                    style="border-radius:0.75rem;padding:0.75rem;" id="verifyBtn"
                                                    disabled>Verify & Complete</button>
                                            </form>
                                            <form action="${pageContext.request.contextPath}/resendOtp" method="post"
                                                class="text-center" onsubmit="return handleResendSubmit(this)">
                                                <p class="text-sm text-gray-500">Didn't receive the code? <button
                                                        type="submit" id="resendBtn"
                                                        class="font-medium text-emerald-600"
                                                        style="background:none;border:none;cursor:pointer;">Resend
                                                        Code</button></p>
                                            </form>
                        </div>
                    </div>
                </div>
            </div>
            <script>
                function otpNext(el, idx) {
                    if (isNaN(el.value)) { el.value = ''; return; }
                    if (el.value && el.nextElementSibling) el.nextElementSibling.focus();
                    updateOtp();
                }
                function otpBack(e, idx) {
                    if (e.key === 'Backspace' && !e.target.value && e.target.previousElementSibling) e.target.previousElementSibling.focus();
                }
                function updateOtp() {
                    var inputs = document.querySelectorAll('.otp-input');
                    var otp = '';
                    inputs.forEach(function (i) { otp += i.value; });
                    document.getElementById('otpHidden').value = otp;
                    document.getElementById('verifyBtn').disabled = otp.length !== 6;
                }
                document.getElementById('otpForm').addEventListener('submit', function () { updateOtp(); });
                function handleVerifySubmit() {
                    var btn = document.getElementById('verifyBtn');
                    if (btn.dataset.loading === 'true') return false;
                    btn.dataset.loading = 'true';
                    btn.disabled = true;
                    btn.innerHTML = '<span class="spinner"></span> Verifying...';
                    updateOtp();
                    return true;
                }
                function handleResendSubmit(form) {
                    var btn = document.getElementById('resendBtn');
                    if (btn.disabled) return false;
                    btn.disabled = true;
                    btn.style.opacity = '0.6';
                    btn.innerHTML = '<span class="spinner" style="width:12px;height:12px;border-width:2px;"></span> Sending...';
                    var expiry = Math.floor(Date.now() / 1000) + 60;
                    localStorage.setItem('otpResendTimer', expiry.toString());
                    return true;
                }

                function startResendCountdown() {
                    var btn = document.getElementById('resendBtn');
                    if (!btn) return;
                    var timeLeft = localStorage.getItem('otpResendTimer');
                    if (!timeLeft) {
                        var expiry = Math.floor(Date.now() / 1000) + 60;
                        localStorage.setItem('otpResendTimer', expiry.toString());
                        timeLeft = expiry.toString();
                    }
                    var now = Math.floor(Date.now() / 1000);
                    var expiryInt = parseInt(timeLeft, 10);
                    if (now >= expiryInt) {
                        localStorage.removeItem('otpResendTimer');
                        btn.disabled = false;
                        btn.style.opacity = '1';
                        btn.innerHTML = 'Resend Code';
                        return;
                    }
                    var remaining = expiryInt - now;
                    btn.disabled = true;
                    btn.style.opacity = '0.6';
                    btn.innerHTML = 'Resend Code (' + remaining + 's)';
                    setTimeout(startResendCountdown, 1000);
                }

                document.addEventListener('DOMContentLoaded', function () {
                    startResendCountdown();
                });
            </script>
    </body>

    </html>