<%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <!DOCTYPE html>
    <html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Sign Up - Zen Tea</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    </head>

    <body>
        <% String signupError=(String) request.getAttribute("errorMessage"); %>
            <div class="auth-layout">
                <div class="auth-image">
                    <img src="https://images.unsplash.com/photo-1556679343-c7306c1976bc?ixlib=rb-4.0.3&auto=format&fit=crop&w=1000&q=80"
                        alt="Zen Tea">
                    <div class="auth-image-overlay"></div>
                    <div class="auth-image-content">
                        <a href="${pageContext.request.contextPath}/index" class="logo">Zen Tea</a>
                        <h2>Join the Zen Tea Family</h2>
                        <p>Create an account to start earning rewards, save your favorite orders, and enjoy exclusive
                            offers.</p>
                    </div>
                </div>
                <div class="auth-form-side">
                    <div class="auth-form-container">
                        <div class="auth-card">
                            <a href="${pageContext.request.contextPath}/index"
                                class="text-sm font-medium text-emerald-600 mb-6"
                                style="display:inline-flex;align-items:center;gap:0.25rem;">&larr; Back to home</a>
                            <h1 class="font-serif text-3xl font-bold text-center mb-2">Create Account</h1>
                            <p class="text-gray-500 text-center mb-8">Join us to start earning rewards today.</p>

                            <% if (signupError !=null) { %>
                                <div class="alert alert-error mb-4">&#10007; <%= signupError %>
                                </div>
                                <% } %>

                                    <form action="${pageContext.request.contextPath}/signUp" method="post"
                                        id="signupForm" onsubmit="return handleSignupSubmit(this)">
                                        <div class="form-group">
                                            <label class="form-label" for="username">Full Name</label>
                                            <input type="text" id="username" name="username" class="form-input"
                                                placeholder="John Doe" required>
                                        </div>
                                        <div class="form-group">
                                            <label class="form-label" for="email">Email Address</label>
                                            <input type="email" id="email" name="email" class="form-input"
                                                placeholder="you@example.com" required>
                                        </div>
                                        <div class="form-group">
                                            <label class="form-label" for="phonenumber">Phone Number</label>
                                            <input type="tel" id="phonenumber" name="phonenumber" class="form-input"
                                                placeholder="+60" required>
                                        </div>
                                        <div class="form-group">
                                            <label class="form-label" for="password">Password</label>
                                            <div style="position:relative;">
                                                <input type="password" id="password" name="password" class="form-input"
                                                    placeholder="&#8226;&#8226;&#8226;&#8226;&#8226;&#8226;&#8226;&#8226;"
                                                    required oninput="checkPassword()">
                                                <button type="button" onclick="togglePassword()"
                                                    style="position:absolute;right:0.75rem;top:50%;transform:translateY(-50%);background:none;border:none;cursor:pointer;color:#9CA3AF;">
                                                    <svg id="eyeIcon" width="20" height="20" fill="none"
                                                        stroke="currentColor" stroke-width="2" viewBox="0 0 24 24">
                                                        <path d="M1 12s4-8 11-8 11 8 11 8-4 8-11 8-11-8-11-8z" />
                                                        <circle cx="12" cy="12" r="3" />
                                                    </svg>
                                                </button>
                                            </div>
                                            <div class="pwd-req" id="pwdReq">
                                                <div class="pwd-req-item" id="req-len">&#10007; At least 8 characters
                                                </div>
                                                <div class="pwd-req-item" id="req-upper">&#10007; Uppercase letter</div>
                                                <div class="pwd-req-item" id="req-lower">&#10007; Lowercase letter</div>
                                                <div class="pwd-req-item" id="req-digit">&#10007; A digit</div>
                                                <div class="pwd-req-item" id="req-special">&#10007; Special character
                                                </div>
                                            </div>
                                        </div>
                                        <div class="form-check mb-6">
                                            <input type="checkbox" id="terms" name="terms" required>
                                            <label for="terms" class="text-sm text-gray-700">I agree to the <a href="#"
                                                    class="text-emerald-600">Terms of Service</a> and <a href="#"
                                                    class="text-emerald-600">Privacy Policy</a></label>
                                        </div>
                                        <button type="submit" id="submitBtn" class="btn btn-emerald btn-block"
                                            style="border-radius:0.75rem;padding:0.75rem;" disabled>Create
                                            Account</button>
                                    </form>
                                    <div class="mt-8 text-center">
                                        <p class="text-sm text-gray-600">Already have an account? <a
                                                href="${pageContext.request.contextPath}/login.jsp"
                                                class="font-medium text-emerald-600">Sign in</a></p>
                                    </div>
                        </div>
                    </div>
                </div>
            </div>
            <script>
                function checkPassword() {
                    var p = document.getElementById('password').value;
                    var reqs = [
                        { id: 'req-len', test: p.length >= 8 },
                        { id: 'req-upper', test: /[A-Z]/.test(p) },
                        { id: 'req-lower', test: /[a-z]/.test(p) },
                        { id: 'req-digit', test: /\d/.test(p) },
                        { id: 'req-special', test: /[^A-Za-z0-9]/.test(p) }
                    ];
                    var allMet = true;
                    reqs.forEach(function (r) {
                        var el = document.getElementById(r.id);
                        if (r.test) { el.classList.add('met'); el.innerHTML = '&#10003; ' + el.textContent.substring(2); }
                        else { el.classList.remove('met'); el.innerHTML = '&#10007; ' + el.textContent.substring(2); allMet = false; }
                    });
                    document.getElementById('submitBtn').disabled = !allMet;
                }
                function togglePassword() {
                    var input = document.getElementById('password');
                    input.type = input.type === 'password' ? 'text' : 'password';
                }
                function handleSignupSubmit(form) {
                    var btn = document.getElementById('submitBtn');
                    if (btn.dataset.loading === 'true') return false;
                    btn.dataset.loading = 'true';
                    btn.disabled = true;
                    btn.innerHTML = '<span class="spinner"></span> Creating Account...';
                    return true;
                }
            </script>
    </body>

    </html>