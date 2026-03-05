<%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <%@ page import="Models.User" %>
        <%@ include file="Layout/header.jsp" %>

            <% User user=(User) request.getAttribute("user"); Integer uid=(Integer)
                session.getAttribute("loggedInUserID"); if (uid==null) { response.sendRedirect("index"); return; } %>

                <main class="container py-12">
                    <h1 class="font-serif text-3xl font-bold mb-8">My Account</h1>
                    <div class="account-layout">
                        <%@ include file="Layout/account-sidebar.jsp" %>
                            <div class="account-content">
                                <div class="card" style="border-radius:1rem;padding:2rem;">
                                    <h2 class="text-xl font-bold mb-2">Security Settings</h2>
                                    <p class="text-gray-500 mb-6">Update your password to keep your account secure.</p>

                                    <form action="${pageContext.request.contextPath}/changePassword" method="post">
                                        <div class="form-group">
                                            <label class="form-label" for="currentpassword">Current Password</label>
                                            <input type="password" id="currentpassword" name="currentpassword"
                                                class="form-input"
                                                placeholder="&#8226;&#8226;&#8226;&#8226;&#8226;&#8226;&#8226;&#8226;"
                                                required>
                                        </div>
                                        <div class="form-group">
                                            <label class="form-label" for="newpassword">New Password</label>
                                            <input type="password" id="newpassword" name="newpassword"
                                                class="form-input"
                                                placeholder="&#8226;&#8226;&#8226;&#8226;&#8226;&#8226;&#8226;&#8226;"
                                                required oninput="checkChangePwd()">
                                            <div class="pwd-req" id="pwdReq">
                                                <div class="pwd-req-item" id="c-len">&#10007; At least 8 characters
                                                </div>
                                                <div class="pwd-req-item" id="c-upper">&#10007; Uppercase letter</div>
                                                <div class="pwd-req-item" id="c-lower">&#10007; Lowercase letter</div>
                                                <div class="pwd-req-item" id="c-digit">&#10007; A digit</div>
                                                <div class="pwd-req-item" id="c-special">&#10007; Special character
                                                </div>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="form-label" for="confirmPassword">Confirm New Password</label>
                                            <input type="password" id="confirmPassword" name="confirmPassword"
                                                class="form-input"
                                                placeholder="&#8226;&#8226;&#8226;&#8226;&#8226;&#8226;&#8226;&#8226;"
                                                required oninput="checkCpMatch()">
                                            <p class="form-error hidden" id="cpMatchErr">Passwords do not match.</p>
                                        </div>
                                        <div class="flex justify-between items-center"
                                            style="margin-top:2rem;padding-top:1.5rem;border-top:1px solid #F3F4F6;">
                                            <a href="${pageContext.request.contextPath}/profile"
                                                class="text-sm font-medium text-gray-500">Cancel</a>
                                            <button type="submit" class="btn btn-emerald"
                                                style="border-radius:0.75rem;">Update Password</button>
                                        </div>
                                    </form>
                                </div>
                            </div>
                    </div>
                </main>

                <script>
                    function checkChangePwd() {
                        var p = document.getElementById('newpassword').value;
                        var reqs = [{ id: 'c-len', test: p.length >= 8 }, { id: 'c-upper', test: /[A-Z]/.test(p) }, { id: 'c-lower', test: /[a-z]/.test(p) }, { id: 'c-digit', test: /\d/.test(p) }, { id: 'c-special', test: /[^A-Za-z0-9]/.test(p) }];
                        reqs.forEach(function (r) { var el = document.getElementById(r.id); if (r.test) { el.classList.add('met'); } else { el.classList.remove('met'); } });
                        checkCpMatch();
                    }
                    function checkCpMatch() {
                        var p1 = document.getElementById('newpassword').value;
                        var p2 = document.getElementById('confirmPassword').value;
                        var err = document.getElementById('cpMatchErr');
                        if (p2 && p1 !== p2) err.classList.remove('hidden'); else err.classList.add('hidden');
                    }
                </script>

                <%@ include file="Layout/footer.jsp" %>