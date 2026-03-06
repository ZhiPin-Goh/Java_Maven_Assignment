<%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <%@ page import="java.util.List" %>
        <%@ page import="Models.Beverage" %>
            <%@ page import="ModelsDTO.TransactionPreparingDTO" %>
                <%@ page import="ModelsDTO.TransactionPreparingItemDTO" %>
                    <%@ include file="Layout/header.jsp" %>
                        <% List<Beverage> beverages = (List<Beverage>) request.getAttribute("beverages");
                                List<TransactionPreparingDTO> preparing = (List<TransactionPreparingDTO>)
                                        request.getAttribute("preparing");
                                        int preparingCount = 0;
                                        int readyCount = 0;
                                        if (preparing != null) {
                                        for (TransactionPreparingDTO p : preparing) {
                                        String s = p.getStatus();
                                        if ("Preparing".equals(s)) preparingCount++;
                                        if ("Ready".equals(s) || "Ready for Pickup".equals(s)) readyCount++;
                                        }
                                        }
                                        %>

                                        <main>
                                            <!-- Hero Section -->
                                            <!-- Hero Carousel Section -->
                                            <section class="hero carousel-hero">
                                                <div class="carousel-slides" id="heroCarousel">
                                                    <!-- Slide 1 -->
                                                    <div class="carousel-slide active">
                                                        <div class="hero-content">
                                                            <div class="animate-in">
                                                                <div class="hero-tag">
                                                                    <svg width="16" height="16" fill="none"
                                                                        stroke="currentColor" stroke-width="2"
                                                                        viewBox="0 0 24 24">
                                                                        <path
                                                                            d="M12 3l1.912 5.813h6.124l-4.962 3.574L17 18.2l-5-3.574L7 18.2l1.926-5.813L3.964 8.813h6.124z" />
                                                                    </svg>
                                                                    <span>Premium Artisan Tea</span>
                                                                </div>
                                                                <h1>Find Your Zen in <br><span>Every Sip.</span></h1>
                                                                <p>Crafted with ethically sourced ingredients to bring
                                                                    balance
                                                                    and harmony to your busy day. Experience the art of
                                                                    tea.</p>
                                                                <div class="flex gap-4" style="flex-wrap:wrap;">
                                                                    <a href="${pageContext.request.contextPath}/drinks"
                                                                        class="btn btn-primary btn-lg">Order Now <svg
                                                                            width="20" height="20" fill="none"
                                                                            stroke="currentColor" stroke-width="2"
                                                                            viewBox="0 0 24 24"
                                                                            style="margin-left:0.5rem;">
                                                                            <path d="M5 12h14M12 5l7 7-7 7" />
                                                                        </svg></a>
                                                                    <a href="#status"
                                                                        class="btn btn-outline btn-lg">Track
                                                                        Order</a>
                                                                </div>
                                                            </div>
                                                            <div class="hero-img-wrap animate-in"
                                                                style="animation-delay:0.2s;">
                                                                <img src="https://images.unsplash.com/photo-1515823662972-da6a2e4d3002?ixlib=rb-4.0.3&auto=format&fit=crop&w=800&q=80"
                                                                    alt="Matcha Drink">
                                                                <div class="hero-img-overlay"></div>
                                                            </div>
                                                        </div>
                                                    </div>

                                                    <!-- Slide 2 -->
                                                    <div class="carousel-slide">
                                                        <div class="hero-content">
                                                            <div class="animate-in">
                                                                <div class="hero-tag">
                                                                    <svg width="16" height="16" fill="none"
                                                                        stroke="currentColor" stroke-width="2"
                                                                        viewBox="0 0 24 24">
                                                                        <path
                                                                            d="M12 3v18M5.25 10.5h13.5M5.25 13.5h13.5" />
                                                                    </svg>
                                                                    <span>New Arrival</span>
                                                                </div>
                                                                <h1>Awaken Your Senses with <br><span>Earl Grey.</span>
                                                                </h1>
                                                                <p>A classic blend elevated to new heights. Discover the
                                                                    rich, citrusy notes of our premium bergamot oil
                                                                    infused black tea.</p>
                                                                <div class="flex gap-4" style="flex-wrap:wrap;">
                                                                    <a href="${pageContext.request.contextPath}/drinks"
                                                                        class="btn btn-primary btn-lg">View Drinks</a>
                                                                </div>
                                                            </div>
                                                            <div class="hero-img-wrap animate-in"
                                                                style="animation-delay:0.2s;">
                                                                <img src="https://images.unsplash.com/photo-1544787219-7f47ccb76574?w=800&q=80"
                                                                    alt="Earl Grey Tea">
                                                                <div class="hero-img-overlay"></div>
                                                            </div>
                                                        </div>
                                                    </div>

                                                    <!-- Slide 3 -->
                                                    <div class="carousel-slide">
                                                        <div class="hero-content">
                                                            <div class="animate-in">
                                                                <div class="hero-tag">
                                                                    <svg width="16" height="16" fill="none"
                                                                        stroke="currentColor" stroke-width="2"
                                                                        viewBox="0 0 24 24">
                                                                        <path
                                                                            d="M11 4H4a2 2 0 0 0-2 2v14a2 2 0 0 0 2 2h14a2 2 0 0 0 2-2v-7" />
                                                                        <path
                                                                            d="M18.5 2.5a2.121 2.121 0 0 1 3 3L12 15l-4 1 1-4 9.5-9.5z" />
                                                                    </svg>
                                                                    <span>Special Offer</span>
                                                                </div>
                                                                <h1>Buy 1 Get 1 Free <br><span>On All Pastries.</span>
                                                                </h1>
                                                                <p>Pair your perfect cup of tea with our freshly baked
                                                                    croissants and pastries. Perfect for a cozy
                                                                    afternoon.</p>
                                                                <div class="flex gap-4" style="flex-wrap:wrap;">
                                                                    <a href="${pageContext.request.contextPath}/locations.jsp"
                                                                        class="btn btn-primary btn-lg">Find a Store</a>
                                                                </div>
                                                            </div>
                                                            <div class="hero-img-wrap animate-in"
                                                                style="animation-delay:0.2s;">
                                                                <img src="https://images.unsplash.com/photo-1576092762791-dd9e2220abd1?w=800&q=80"
                                                                    alt="Delicious Pastries">
                                                                <div class="hero-img-overlay"></div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>

                                                <!-- Controls -->
                                                <button class="carousel-btn prev" onclick="moveSlide(-1)">
                                                    <svg width="24" height="24" fill="none" stroke="currentColor"
                                                        stroke-width="2" viewBox="0 0 24 24">
                                                        <path d="M15 18l-6-6 6-6" />
                                                    </svg>
                                                </button>
                                                <button class="carousel-btn next" onclick="moveSlide(1)">
                                                    <svg width="24" height="24" fill="none" stroke="currentColor"
                                                        stroke-width="2" viewBox="0 0 24 24">
                                                        <path d="M9 18l6-6-6-6" />
                                                    </svg>
                                                </button>

                                                <div class="carousel-dots" id="heroDots">
                                                    <span class="dot active" onclick="currentSlide(0)"></span>
                                                    <span class="dot" onclick="currentSlide(1)"></span>
                                                    <span class="dot" onclick="currentSlide(2)"></span>
                                                </div>
                                            </section>

                                            <script>
                                                let slideIndex = 0;
                                                const slides = document.querySelectorAll('.carousel-slide');
                                                const dots = document.querySelectorAll('.carousel-dots .dot');
                                                let autoPlayTimer;

                                                function showSlide(n) {
                                                    slides.forEach(slide => slide.classList.remove('active'));
                                                    dots.forEach(dot => dot.classList.remove('active'));

                                                    slideIndex = (n + slides.length) % slides.length;

                                                    slides[slideIndex].classList.add('active');
                                                    dots[slideIndex].classList.add('active');
                                                }

                                                function moveSlide(step) {
                                                    showSlide(slideIndex + step);
                                                    resetAutoPlay();
                                                }

                                                function currentSlide(n) {
                                                    showSlide(n);
                                                    resetAutoPlay();
                                                }

                                                function resetAutoPlay() {
                                                    clearInterval(autoPlayTimer);
                                                    autoPlayTimer = setInterval(() => moveSlide(1), 5000);
                                                }

                                                // Initialize autoplay
                                                resetAutoPlay();
                                            </script>

                                            <!-- Live Order Status -->
                                            <% if (preparing !=null && !preparing.isEmpty()) { %>
                                                <section id="status" class="container"
                                                    style="margin-top:-2.5rem;margin-bottom:4rem;position:relative;z-index:20;max-width:64rem;">
                                                    <div class="live-status animate-in">
                                                        <div class="live-status-info">
                                                            <h2 class="font-serif">Live Status</h2>
                                                            <p class="text-gray-500 mb-6">Track your order progress in
                                                                real-time.</p>
                                                            <div style="display:flex;flex-direction:column;gap:1rem;">
                                                                <div class="status-counter">
                                                                    <div class="flex items-center gap-2">
                                                                        <span class="status-dot preparing"></span>
                                                                        <span
                                                                            class="font-medium text-gray-700">Preparing</span>
                                                                    </div>
                                                                    <span
                                                                        style="font-family:monospace;font-weight:700;color:#EA580C;">
                                                                        <%= preparingCount %>
                                                                    </span>
                                                                </div>
                                                                <div class="status-counter">
                                                                    <div class="flex items-center gap-2">
                                                                        <span class="status-dot ready"></span>
                                                                        <span
                                                                            class="font-medium text-gray-700">Ready</span>
                                                                    </div>
                                                                    <span
                                                                        style="font-family:monospace;font-weight:700;color:#059669;">
                                                                        <%= readyCount %>
                                                                    </span>
                                                                </div>
                                                            </div>
                                                        </div>
                                                        <div class="live-status-list">
                                                            <% for (TransactionPreparingDTO order : preparing) { boolean
                                                                isReady="Ready" .equals(order.getStatus())
                                                                || "Ready for Pickup" .equals(order.getStatus()); String
                                                                iconClass=isReady ? "ready" : "preparing" ; String
                                                                badgeClass=isReady ? "ready" : "preparing" ; %>
                                                                <div class="order-row">
                                                                    <div class="flex items-center gap-4">
                                                                        <div class="order-icon <%= iconClass %>">
                                                                            <% if (isReady) { %>
                                                                                <svg width="20" height="20" fill="none"
                                                                                    stroke="currentColor"
                                                                                    stroke-width="2"
                                                                                    viewBox="0 0 24 24">
                                                                                    <path
                                                                                        d="M22 11.08V12a10 10 0 1 1-5.93-9.14" />
                                                                                    <path d="M22 4 12 14.01l-3-3" />
                                                                                </svg>
                                                                                <% } else { %>
                                                                                    <svg width="20" height="20"
                                                                                        fill="none"
                                                                                        stroke="currentColor"
                                                                                        stroke-width="2"
                                                                                        viewBox="0 0 24 24">
                                                                                        <circle cx="12" cy="12"
                                                                                            r="10" />
                                                                                        <polyline
                                                                                            points="12 6 12 12 16 14" />
                                                                                    </svg>
                                                                                    <% } %>
                                                                        </div>
                                                                        <div>
                                                                            <div class="flex items-center gap-2">
                                                                                <span class="font-bold text-gray-900">
                                                                                    <%= order.getPickupCode() %>
                                                                                </span>
                                                                            </div>
                                                                            <p class="text-xs text-gray-400">
                                                                                <%= order.getOrderTime() %>
                                                                            </p>
                                                                        </div>
                                                                    </div>
                                                                    <span class="status-badge <%= badgeClass %>">
                                                                        <% if (!isReady) { %><span
                                                                                class="status-dot preparing"
                                                                                style="width:6px;height:6px;"></span>
                                                                            <% } %>
                                                                                <%= order.getStatus() %>
                                                                    </span>
                                                                </div>
                                                                <% } %>
                                                        </div>
                                                    </div>
                                                </section>
                                                <% } %>

                                                    <!-- Menu Section -->
                                                    <section class="container" style="padding-bottom:5rem;">
                                                        <div class="text-center mb-12 animate-in">
                                                            <h2 class="font-serif text-4xl font-bold mb-4">Curated
                                                                Selections</h2>
                                                            <p class="text-gray-500"
                                                                style="max-width:40rem;margin:0 auto;">Discover our
                                                                hand-crafted beverages, made to order with the finest
                                                                ingredients.</p>
                                                        </div>

                                                        <div class="flex justify-center mb-12">
                                                            <div class="filter-tabs">
                                                                <button class="filter-tab active"
                                                                    onclick="filterCategory('all', this)">All</button>
                                                                <% java.util.LinkedHashSet<String> categories = new
                                                                    java.util.LinkedHashSet<>();
                                                                        if (beverages != null) {
                                                                        for (Beverage bev : beverages) {
                                                                        if (bev.isAvailable() &&
                                                                        bev.getBeverageCategory() != null
                                                                        && !bev.getBeverageCategory().equals("N/A")
                                                                        && !bev.getBeverageCategory().isEmpty()) {
                                                                        categories.add(bev.getBeverageCategory());
                                                                        }
                                                                        }
                                                                        }
                                                                        for (String cat : categories) {
                                                                        %>
                                                                        <button class="filter-tab"
                                                                            onclick="filterCategory('<%= cat %>', this)">
                                                                            <%= cat %>
                                                                        </button>
                                                                        <% } %>
                                                            </div>
                                                        </div>

                                                        <% if (beverages !=null && !beverages.isEmpty()) { %>
                                                            <div class="product-grid">
                                                                <% for (Beverage b : beverages) { if (!b.isAvailable())
                                                                    continue; %>
                                                                    <div class="card animate-in"
                                                                        data-category="<%= b.getBeverageCategory() %>">
                                                                        <div class="card-img">
                                                                            <a
                                                                                href="${pageContext.request.contextPath}/beverageDetails?id=<%= b.getID() %>">
                                                                                <img src="<%= b.getBeverageImagePath() %>"
                                                                                    alt="<%= b.getBeverageName() %>">
                                                                            </a>
                                                                            <% if (b.getBeverageCategory() !=null &&
                                                                                !b.getBeverageCategory().equals("N/A"))
                                                                                { %>
                                                                                <div class="card-tag">
                                                                                    <%= b.getBeverageCategory() %>
                                                                                </div>
                                                                                <% } %>
                                                                                    <a href="${pageContext.request.contextPath}/beverageDetails?id=<%= b.getID() %>"
                                                                                        class="card-add-btn">+</a>
                                                                        </div>
                                                                        <div class="card-body">
                                                                            <div
                                                                                class="flex justify-between items-center mb-2">
                                                                                <a
                                                                                    href="${pageContext.request.contextPath}/beverageDetails?id=<%= b.getID() %>">
                                                                                    <h3>
                                                                                        <%= b.getBeverageName() %>
                                                                                    </h3>
                                                                                </a>
                                                                                <span class="card-price">RM <%=
                                                                                        String.format("%.2f",
                                                                                        b.getPrice()) %></span>
                                                                            </div>
                                                                            <p class="card-desc line-clamp-2">
                                                                                <%= b.getBeverageDescription() %>
                                                                            </p>
                                                                            <div class="card-badges">
                                                                                <% if (b.isHasIceOption()) { %>
                                                                                    <span>Iced</span>
                                                                                    <% } %>
                                                                                        <% if (b.isHasHotOption()) { %>
                                                                                            <span>Hot</span>
                                                                                            <% } %>
                                                                            </div>
                                                                        </div>
                                                                    </div>
                                                                    <% } %>
                                                            </div>
                                                            <% } else { %>
                                                                <div class="text-center p-8">
                                                                    <p class="text-gray-500">No beverages available at
                                                                        the moment.</p>
                                                                </div>
                                                                <% } %>
                                                    </section>
                                        </main>

                                        <script>
                                            function filterCategory(cat, btn) {
                                                document.querySelectorAll('.filter-tab').forEach(function (t) { t.classList.remove('active'); });
                                                btn.classList.add('active');
                                                document.querySelectorAll('.product-grid .card').forEach(function (c) {
                                                    if (cat === 'all' || c.dataset.category === cat) { c.style.display = ''; }
                                                    else { c.style.display = 'none'; }
                                                });
                                            }
                                        </script>

                                        <%@ include file="Layout/footer.jsp" %>