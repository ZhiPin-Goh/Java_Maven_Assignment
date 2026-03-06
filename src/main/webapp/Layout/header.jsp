<%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <% String currentPath=request.getServletPath(); Integer loggedInUserID=(Integer)
        session.getAttribute("loggedInUserID"); String successMsg=(String) session.getAttribute("successMessage");
        String errorMsg=(String) session.getAttribute("errorMessage"); if (successMsg !=null)
        session.removeAttribute("successMessage"); if (errorMsg !=null) session.removeAttribute("errorMessage"); String
        homeActive=(currentPath.equals("/index") || currentPath.equals("/index.jsp")) ? "active" : "" ; String
        drinkActive=(currentPath.equals("/drinks") || currentPath.equals("/drinks.jsp")) ? "active" : "" ; String
        cartActive=currentPath.equals("/cart") ? "active" : "" ; String
        locationActive=currentPath.equals("/locations.jsp") ? "active" : "" ; %>
        <!DOCTYPE html>
        <html lang="en">

        <head>
            <meta charset="UTF-8">
            <meta name="viewport" content="width=device-width, initial-scale=1.0">
            <title>Zen Tea - Premium Artisan Tea</title>
            <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
        </head>

        <body>
            <nav class="navbar">
                <div class="nav-inner">
                    <div class="flex items-center gap-4">
                        <button class="mobile-menu-btn" onclick="toggleMobileMenu()">
                            <svg width="24" height="24" fill="none" stroke="currentColor" stroke-width="2"
                                viewBox="0 0 24 24">
                                <path d="M4 6h16M4 12h16M4 18h16" />
                            </svg>
                        </button>
                        <a href="${pageContext.request.contextPath}/index" class="logo">Zen Tea</a>
                        <div class="nav-links">
                            <a href="${pageContext.request.contextPath}/index" class="<%= homeActive %>">Home</a>
                            <a href="${pageContext.request.contextPath}/drinks" class="<%= drinkActive %>">Drink</a>
                            <a href="${pageContext.request.contextPath}/cart" class="<%= cartActive %>">Cart</a>
                            <a href="${pageContext.request.contextPath}/locations.jsp"
                                class="<%= locationActive %>">Location</a>
                        </div>
                    </div>
                    <div class="nav-right">
                        <form class="search-form" action="${pageContext.request.contextPath}/searchBeverage"
                            method="get">
                            <svg class="search-icon" fill="none" stroke="currentColor" stroke-width="2"
                                viewBox="0 0 24 24">
                                <circle cx="11" cy="11" r="8" />
                                <path d="m21 21-4.3-4.3" />
                            </svg>
                            <input type="text" name="keyword" class="search-input" placeholder="Search beverages..."
                                value="${param.keyword}">
                        </form>
                        <a href="${pageContext.request.contextPath}/cart" class="icon-btn">
                            <svg width="24" height="24" fill="none" stroke="currentColor" stroke-width="2"
                                viewBox="0 0 24 24">
                                <path d="M6 2 3 6v14a2 2 0 0 0 2 2h14a2 2 0 0 0 2-2V6l-3-4z" />
                                <line x1="3" x2="21" y1="6" y2="6" />
                                <path d="M16 10a4 4 0 0 1-8 0" />
                            </svg>
                        </a>
                        <% if (loggedInUserID !=null) { %>
                            <a href="${pageContext.request.contextPath}/profile" class="icon-btn">
                                <svg width="24" height="24" fill="none" stroke="currentColor" stroke-width="2"
                                    viewBox="0 0 24 24">
                                    <path d="M20 21v-2a4 4 0 0 0-4-4H8a4 4 0 0 0-4 4v2" />
                                    <circle cx="12" cy="7" r="4" />
                                </svg>
                            </a>
                            <% } else { %>
                                <a href="${pageContext.request.contextPath}/login.jsp"
                                    class="btn btn-sm btn-emerald">Sign In</a>
                                <% } %>
                    </div>
                </div>
                <div class="mobile-menu" id="mobileMenu">
                    <a href="${pageContext.request.contextPath}/index" class="<%= homeActive %>">Home</a>
                    <a href="${pageContext.request.contextPath}/drinks" class="<%= drinkActive %>">Drink</a>
                    <a href="${pageContext.request.contextPath}/cart" class="<%= cartActive %>">Cart</a>
                    <a href="${pageContext.request.contextPath}/locations.jsp"
                        class="<%= locationActive %>">Location</a>
                    <div class="mobile-search">
                        <form action="${pageContext.request.contextPath}/searchBeverage" method="get"
                            style="position:relative;">
                            <svg style="position:absolute;left:0.75rem;top:50%;transform:translateY(-50%);color:#9CA3AF;width:16px;height:16px;"
                                fill="none" stroke="currentColor" stroke-width="2" viewBox="0 0 24 24">
                                <circle cx="11" cy="11" r="8" />
                                <path d="m21 21-4.3-4.3" />
                            </svg>
                            <input type="text" name="keyword" placeholder="Search..."
                                style="width:100%;padding:0.5rem 1rem 0.5rem 2.5rem;border:1px solid #D1D5DB;border-radius:9999px;font-size:0.875rem;">
                        </form>
                    </div>
                </div>
            </nav>

            <% if (successMsg !=null) { %>
                <div class="container" style="margin-top:1rem;">
                    <div class="alert alert-success">&#10003; <%= successMsg %>
                    </div>
                </div>
                <% } %>
                    <% if (errorMsg !=null) { %>
                        <div class="container" style="margin-top:1rem;">
                            <div class="alert alert-error">&#10007; <%= errorMsg %>
                            </div>
                        </div>
                        <% } %>
                            <% String reqError=(String) request.getAttribute("errorMessage"); if (reqError !=null) { %>
                                <div class="container" style="margin-top:1rem;">
                                    <div class="alert alert-error">&#10007; <%= reqError %>
                                    </div>
                                </div>
                                <% } %>

                                    <script>
                                        function toggleMobileMenu() {
                                            var menu = document.getElementById('mobileMenu');
                                            menu.style.display = menu.style.display === 'block' ? 'none' : 'block';
                                        }
                                    </script>