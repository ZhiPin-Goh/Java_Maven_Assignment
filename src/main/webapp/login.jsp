<%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <!DOCTYPE html>
    <html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Sign In - Zen Tea</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    </head>

    <body>
        <% String loginSuccess=(String) session.getAttribute("successMessage"); String loginError=(String)
            request.getAttribute("errorMessage"); if (loginSuccess !=null) session.removeAttribute("successMessage"); %>
            <div class="auth-layout">
                <div class="auth-image">
                    <img src="https://images.unsplash.com/photo-1556679343-c7306c1976bc?ixlib=rb-4.0.3&auto=format&fit=crop&w=1000&q=80"
                        alt="Zen Tea">
                    <div class="auth-image-overlay"></div>
                    <div class="auth-image-content">
                        <a href="${pageContext.request.contextPath}/index" class="logo">Zen Tea</a>
                        <h2>Welcome Back</h2>
                        <p>Sign in to your account to order your favorite artisan teas and earn rewards.</p>
                    </div>
                </div>
                <div class="auth-form-side">
                    <div class="auth-form-container">
                        <a href="${pageContext.request.contextPath}/index" class="logo"
                            style="display:none;color:#065F46;text-align:center;margin-bottom:2rem;">Zen Tea</a>
                        <div class="auth-card">
                            <a href="${pageContext.request.contextPath}/index"
                                class="text-sm font-medium text-emerald-600 mb-6"
                                style="display:inline-flex;align-items:center;gap:0.25rem;">&larr; Back to home</a>
                            <h1 class="font-serif text-3xl font-bold text-center mb-2">Sign In</h1>
                            <p class="text-gray-500 text-center mb-8">Enter your details to access your account.</p>

                            <% if (loginSuccess !=null) { %>
                                <div class="alert alert-success mb-4">&#10003; <%= loginSuccess %>
                                </div>
                                <% } %>
                                    <% if (loginError !=null) { %>
                                        <div class="alert alert-error mb-4">&#10007; <%= loginError %>
                                        </div>
                                        <% } %>

                                            <form action="${pageContext.request.contextPath}/login" method="post">
                                                <div class="form-group">
                                                    <label class="form-label" for="email">Email Address</label>
                                                    <input type="text" id="email" name="email" class="form-input"
                                                        placeholder="you@example.com" required>
                                                </div>
                                                <div class="form-group">
                                                    <div class="flex justify-between items-center mb-2">
                                                        <label class="form-label" for="password"
                                                            style="margin-bottom:0;">Password</label>
                                                        <a href="${pageContext.request.contextPath}/forget-password.jsp"
                                                            class="text-sm font-medium text-emerald-600">Forgot
                                                            password?</a>
                                                    </div>
                                                    <input type="password" id="password" name="password"
                                                        class="form-input"
                                                        placeholder="&#8226;&#8226;&#8226;&#8226;&#8226;&#8226;&#8226;&#8226;"
                                                        required>
                                                </div>
                                                <button type="submit" class="btn btn-emerald btn-block"
                                                    style="border-radius:0.75rem;padding:0.75rem;">Sign In</button>
                                            </form>
                                            <div class="mt-8 text-center">
                                                <p class="text-sm text-gray-600">Don't have an account? <a
                                                        href="${pageContext.request.contextPath}/sign-up.jsp"
                                                        class="font-medium text-emerald-600">Sign up</a></p>
                                            </div>
                        </div>
                    </div>
                </div>
            </div>
    </body>

    </html>