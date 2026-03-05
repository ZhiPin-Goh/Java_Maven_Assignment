<%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <!DOCTYPE html>
    <html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Reset Password - Zen Tea</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    </head>

    <body>
        <% String rpError=(String) request.getAttribute("errorMessage"); if (rpError==null) rpError=(String)
            session.getAttribute("errorMessage"); if (rpError !=null) session.removeAttribute("errorMessage"); String
            resetEmail=(String) session.getAttribute("resetEmail"); %>
            <div class="auth-layout">
                <div class="auth-image">
                    <img src="https://images.unsplash.com/photo-1556679343-c7306c1976bc?ixlib=rb-4.0.3&auto=format&fit=crop&w=1000&q=80"
                        alt="Zen Tea">
                    <div class="auth-image-overlay"></div>
                    <div class="auth-image-content">
                        <a href="${pageContext.request.contextPath}/index" class="logo">Zen Tea</a>
                        <h2>Set New Password</h2>
                        <p>Enter the OTP code sent to your email and choose a new password.</p>
                    </div>
                </div>
                <div class="auth-form-side">
                    <div class="auth-form-container">
                        <div class="auth-card">
                            <a href="${pageContext.request.contextPath}/login.jsp"
                                class="text-sm font-medium text-emerald-600 mb-6"
                                style="display:inline-flex;align-items:center;gap:0.25rem;">&larr; Back to sign in</a>
                            <h1 class="font-serif text-3xl font-bold text-center mb-2">Reset Password</h1>
                            <p class="text-gray-500 text-center mb-8">
                                <% if (resetEmail !=null) { %>Code sent to <span class="font-medium text-gray-900">
                                        <%= resetEmail %>
                                    </span>
                                    <% } else { %>Enter your OTP and new password.<% } %>
                            </p>

                            <% if (rpError !=null) { %>
                                <div class="alert alert-error mb-4">&#10007; <%= rpError %>
                                </div>
                                <% } %>

                                    <form action="${pageContext.request.contextPath}/resetPassword" method="post">
                                        <div class="form-group">
                                            <label class="form-label" for="otp">OTP Code</label>
                                            <input type="text" id="otp" name="otp" class="form-input"
                                                placeholder="Enter 6-digit code" required maxlength="6">
                                        </div>
                                        <div class="form-group">
                                            <label class="form-label" for="newpassword">New Password</label>
                                            <input type="password" id="newpassword" name="newpassword"
                                                class="form-input"
                                                placeholder="&#8226;&#8226;&#8226;&#8226;&#8226;&#8226;&#8226;&#8226;"
                                                required oninput="checkResetPwd()">
                                            <div class="pwd-req" id="pwdReq">
                                                <div class="pwd-req-item" id="r-len">&#10007; At least 8 characters
                                                </div>
                                                <div class="pwd-req-item" id="r-upper">&#10007; Uppercase letter</div>
                                                <div class="pwd-req-item" id="r-lower">&#10007; Lowercase letter</div>
                                                <div class="pwd-req-item" id="r-digit">&#10007; A digit</div>
                                                <div class="pwd-req-item" id="r-special">&#10007; Special character
                                                </div>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="form-label" for="confirmpassword">Confirm Password</label>
                                            <input type="password" id="confirmpassword" name="confirmpassword"
                                                class="form-input"
                                                placeholder="&#8226;&#8226;&#8226;&#8226;&#8226;&#8226;&#8226;&#8226;"
                                                required oninput="checkMatch()">
                                            <p class="form-error hidden" id="matchError">Passwords do not match.</p>
                                        </div>
                                        <button type="submit" class="btn btn-emerald btn-block"
                                            style="border-radius:0.75rem;padding:0.75rem;">Reset Password</button>
                                    </form>
                        </div>
                    </div>
                </div>
            </div>
            <script>
                function checkResetPwd() {
                    var p = document.getElementById('newpassword').value;
                    var reqs = [{ id: 'r-len', test: p.length >= 8 }, { id: 'r-upper', test: /[A-Z]/.test(p) }, { id: 'r-lower', test: /[a-z]/.test(p) }, { id: 'r-digit', test: /\d/.test(p) }, { id: 'r-special', test: /[^A-Za-z0-9]/.test(p) }];
                    reqs.forEach(function (r) { var el = document.getElementById(r.id); if (r.test) { el.classList.add('met'); } else { el.classList.remove('met'); } });
                    checkMatch();
                }
                function checkMatch() {
                    var p1 = document.getElementById('newpassword').value;
                    var p2 = document.getElementById('confirmpassword').value;
                    var err = document.getElementById('matchError');
                    if (p2 && p1 !== p2) { err.classList.remove('hidden'); } else { err.classList.add('hidden'); }
                }
            </script>
    </body>

    </html>