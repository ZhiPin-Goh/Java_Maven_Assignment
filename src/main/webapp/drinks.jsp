<%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <%@ page import="java.util.List" %>
        <%@ page import="Models.Beverage" %>
            <%@ include file="Layout/header.jsp" %>

                <% List<Beverage> beverages = (List<Beverage>) request.getAttribute("beverages");
                        String keyword = request.getParameter("keyword");
                        %>

                        <main class="container py-12">
                            <div class="text-center mb-12">
                                <h1 class="font-serif text-4xl font-bold mb-4">
                                    <% if (keyword !=null && !keyword.isEmpty()) { %>
                                        Search Results for "<%= keyword %>"
                                            <% } else { %>
                                                All Drinks
                                                <% } %>
                                </h1>
                                <p class="text-lg text-gray-600" style="max-width:40rem;margin:0 auto;">
                                    Explore our full menu of handcrafted beverages, made to order with premium
                                    ingredients.
                                </p>
                            </div>

                            <% if (keyword !=null && !keyword.isEmpty()) { %>
                                <div class="mb-8">
                                    <a href="${pageContext.request.contextPath}/index"
                                        class="btn btn-sm btn-outline-emerald">&larr; Back to All Drinks</a>
                                </div>
                                <% } %>

                                    <% if (beverages !=null && !beverages.isEmpty()) { %>

                                        <% if (keyword==null || keyword.isEmpty()) { %>
                                            <div class="flex justify-center mb-8" style="flex-wrap:wrap;">
                                                <div class="filter-tabs">
                                                    <button class="filter-tab active"
                                                        onclick="filterDrinks('all', this)">All</button>
                                                </div>
                                            </div>
                                            <% } %>

                                                <div class="product-grid" id="drinksGrid">
                                                    <% for (Beverage b : beverages) { if (!b.isAvailable()) continue; %>
                                                        <div class="card animate-in"
                                                            data-category="<%= b.getBeverageCategory() %>">
                                                            <div class="card-img" style="height:16rem;">
                                                                <a
                                                                    href="${pageContext.request.contextPath}/beverageDetails?id=<%= b.getID() %>">
                                                                    <img src="http://localhost:5018<%= b.getBeverageImagePath() %>"
                                                                        alt="<%= b.getBeverageName() %>">
                                                                </a>
                                                                <% if (b.getBeverageCategory() !=null &&
                                                                    !b.getBeverageCategory().equals("N/A")) { %>
                                                                    <div class="card-tag">
                                                                        <%= b.getBeverageCategory() %>
                                                                    </div>
                                                                    <% } %>
                                                                        <a href="${pageContext.request.contextPath}/beverageDetails?id=<%= b.getID() %>"
                                                                            class="card-add-btn">+</a>
                                                            </div>
                                                            <div class="card-body" style="padding:1.5rem;">
                                                                <div class="flex justify-between items-center mb-2">
                                                                    <a
                                                                        href="${pageContext.request.contextPath}/beverageDetails?id=<%= b.getID() %>">
                                                                        <h3 style="font-size:1.125rem;">
                                                                            <%= b.getBeverageName() %>
                                                                        </h3>
                                                                    </a>
                                                                    <span class="text-emerald-700 font-medium">RM <%=
                                                                            String.format("%.2f", b.getPrice()) %>
                                                                    </span>
                                                                </div>
                                                                <p class="card-desc line-clamp-2">
                                                                    <%= b.getBeverageDescription() %>
                                                                </p>
                                                                <div class="flex gap-2">
                                                                    <% if (b.isHasIceOption()) { %><span
                                                                            class="text-xs text-gray-400"
                                                                            style="background:#F3F4F6;padding:0.25rem 0.5rem;border-radius:0.25rem;">Iced</span>
                                                                        <% } %>
                                                                            <% if (b.isHasHotOption()) { %><span
                                                                                    class="text-xs text-gray-400"
                                                                                    style="background:#F3F4F6;padding:0.25rem 0.5rem;border-radius:0.25rem;">Hot</span>
                                                                                <% } %>
                                                                </div>
                                                            </div>
                                                        </div>
                                                        <% } %>
                                                </div>

                                                <% } else { %>
                                                    <div class="text-center p-8" style="padding:4rem 0;">
                                                        <p class="text-xl text-gray-500 mb-4">
                                                            <% if (keyword !=null && !keyword.isEmpty()) { %>
                                                                No beverages found for "<%= keyword %>"
                                                                    <% } else { %>
                                                                        No beverages available at the moment.
                                                                        <% } %>
                                                        </p>
                                                        <a href="${pageContext.request.contextPath}/index"
                                                            class="btn btn-emerald">Browse Menu</a>
                                                    </div>
                                                    <% } %>
                        </main>

                        <script>
                            function filterDrinks(cat, btn) {
                                document.querySelectorAll('.filter-tab').forEach(function (t) { t.classList.remove('active'); });
                                btn.classList.add('active');
                                document.querySelectorAll('#drinksGrid .card').forEach(function (c) {
                                    if (cat === 'all' || c.dataset.category === cat) { c.style.display = ''; }
                                    else { c.style.display = 'none'; }
                                });
                            }
                        </script>

                        <%@ include file="Layout/footer.jsp" %>