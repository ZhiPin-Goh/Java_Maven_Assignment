<%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <!DOCTYPE html>
    <html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Forgot Password - Zen Tea</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    </head>

    <body>
        <% String fpError=(String) session.getAttribute("errorMessage"); if (fpError !=null)
            session.removeAttribute("errorMessage"); %>
            <div class="auth-layout">
                <div class="auth-image">
                    <img src="https://images.unsplash.com/photo-1556679343-c7306c1976bc?ixlib=rb-4.0.3&auto=format&fit=crop&w=1000&q=80"
                        alt="Zen Tea">
                    <div class="auth-image-overlay"></div>
                    <div class="auth-image-content">
                        <a href="${pageContext.request.contextPath}/index" class="logo">Zen Tea</a>
                        <h2>Reset Your Password</h2>
                        <p>Enter your email address and we'll send you a verification code to reset your password.</p>
                    </div>
                </div>
                <div class="auth-form-side">
                    <div class="auth-form-container">
                        <div class="auth-card">
                            <a href="${pageContext.request.contextPath}/login.jsp"
                                class="text-sm font-medium text-emerald-600 mb-6"
                                style="display:inline-flex;align-items:center;gap:0.25rem;">&larr; Back to sign in</a>
                            <h1 class="font-serif text-3xl font-bold text-center mb-2">Forgot Password?</h1>
                            <p class="text-gray-500 text-center mb-8">Enter your email address to receive a password
                                reset code.</p>

                            <% if (fpError !=null) { %>
                                <div class="alert alert-error mb-4">&#10007; <%= fpError %>
                                </div>
                                <% } %>

                                    <form action="${pageContext.request.contextPath}/forgetPassword" method="post">
                                        <div class="form-group">
                                            <label class="form-label" for="email">Email Address</label>
                                            <input type="email" id="email" name="email" class="form-input"
                                                placeholder="you@example.com" required>
                                        </div>
                                        <button type="submit" class="btn btn-emerald btn-block"
                                            style="border-radius:0.75rem;padding:0.75rem;">Send Reset Code</button>
                                    </form>
                        </div>
                    </div>
                </div>
            </div>
    </body>

    </html>