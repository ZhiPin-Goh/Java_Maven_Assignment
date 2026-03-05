<%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <%@ page import="Models.User" %>
        <%@ include file="Layout/header.jsp" %>
            <% User user=(User) request.getAttribute("user"); if (user==null) { response.sendRedirect("index"); return;
                } %>

                <main class="container py-12">
                    <h1 class="font-serif text-3xl font-bold mb-8">My Account</h1>
                    <div class="account-layout">
                        <%@ include file="Layout/account-sidebar.jsp" %>
                            <div class="account-content">
                                <div class="card" style="border-radius:1rem;padding:2rem;">
                                    <h2 class="text-xl font-bold mb-6">Profile Information</h2>
                                    <form action="${pageContext.request.contextPath}/editProfile" method="post">
                                        <div class="form-row">
                                            <div class="form-group">
                                                <label class="form-label" for="username">Full Name</label>
                                                <input type="text" id="username" name="username" class="form-input"
                                                    value="<%= user.getUserName() %>" required>
                                            </div>
                                            <div class="form-group">
                                                <label class="form-label" for="email">Email Address</label>
                                                <input type="email" id="email" name="email" class="form-input"
                                                    value="<%= user.getEmail() %>" required>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="form-label" for="phonenumber">Phone Number</label>
                                            <input type="tel" id="phonenumber" name="phonenumber" class="form-input"
                                                value="<%= user.getPhoneNumber() %>" required>
                                        </div>
                                        <div class="flex justify-between items-center"
                                            style="margin-top:2rem;padding-top:1.5rem;border-top:1px solid #F3F4F6;">
                                            <a href="${pageContext.request.contextPath}/index"
                                                class="text-sm font-medium text-gray-500">Cancel</a>
                                            <button type="submit" class="btn btn-emerald"
                                                style="border-radius:0.75rem;">Save Changes</button>
                                        </div>
                                    </form>
                                </div>
                            </div>
                    </div>
                </main>

                <%@ include file="Layout/footer.jsp" %>