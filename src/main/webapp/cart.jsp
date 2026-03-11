<%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <%@ page import="java.util.List" %>
        <%@ page import="ModelsDTO.CartDTO" %>
            <%@ page import="ModelsDTO.GetCartDTO" %>
                <%@ include file="Layout/header.jsp" %>
                    <% GetCartDTO cartData=(GetCartDTO) request.getAttribute("cartData"); List<CartDTO> cartItems =
                        (cartData != null) ? cartData.getItems() : null;
                        String cartError = (String) request.getAttribute("error");
                        double totalAmt = 0;
                        double sstAmt = 0;
                        double subtotalAmt = 0;
                        if (cartData != null) {
                        totalAmt = cartData.getTotalAmount().doubleValue();
                        sstAmt = Math.round((totalAmt / 1.06 * 0.06) * 100.0) / 100.0;
                        subtotalAmt = Math.round((totalAmt - sstAmt) * 100.0) / 100.0;
                        }
                        %>

                        <main class="container py-12">
                            <h1 class="font-serif text-3xl font-bold mb-8">Your Cart</h1>

                            <% if (cartError !=null) { %>
                                <div class="alert alert-error mb-4">&#10007; <%= cartError %>
                                </div>
                                <% } %>

                            <% 
                                java.math.BigDecimal userPointsDecimal = (java.math.BigDecimal) request.getAttribute("userPoints");
                                double userPoints = (userPointsDecimal != null) ? userPointsDecimal.doubleValue() : 0.0;
                            %>

                                    <% if (cartItems !=null && !cartItems.isEmpty()) { %>
                                        <form id="checkoutForm" action="${pageContext.request.contextPath}/checkOut"
                                            method="post">
                                            <div class="cart-layout">
                                                <div class="cart-items">
                                                    <div class="card" style="border-radius:1rem;">
                                                        <% for (CartDTO item : cartItems) { %>
                                                            <div class="cart-item">
                                                                <div class="cart-item-img" style="background:#ECFDF5;">
                                                                    <img src="<%= item.getImagePath() %>"
                                                                        alt="<%= item.getBeverageName() %>">
                                                                </div>
                                                                <div class="cart-item-info" style="flex:1;">
                                                                    <div class="flex justify-between items-center mb-1">
                                                                        <h3 class="text-lg font-bold">
                                                                            <%= item.getBeverageName() %>
                                                                        </h3>
                                                                        <span class="font-bold">RM <%=
                                                                                item.getTotalPrice() %></span>
                                                                    </div>
                                                                    <p class="text-sm text-gray-500 mb-2">
                                                                        <%= item.getDescription() %>
                                                                    </p>
                                                                    <div class="flex justify-between items-center"
                                                                        style="margin-top:0.5rem;">
                                                                        <div class="flex items-center gap-2">
                                                                            <input type="checkbox" name="cardIDs"
                                                                                value="<%= item.getCartID() %>" checked
                                                                                data-price="<%= item.getTotalPrice() %>"
                                                                                onchange="updateCartTotals()"
                                                                                style="accent-color:#059669;width:16px;height:16px;">
                                                                            <div class="cart-qty">
                                                                                <button type="button"
                                                                                    style="padding:0.5rem;"
                                                                                    onclick="cartAction('${pageContext.request.contextPath}/minusQuantity', '<%= item.getCartID() %>', '1')">
                                                                                    <svg width="16" height="16"
                                                                                        fill="none"
                                                                                        stroke="currentColor"
                                                                                        stroke-width="2"
                                                                                        viewBox="0 0 24 24">
                                                                                        <line x1="5" x2="19" y1="12"
                                                                                            y2="12" />
                                                                                    </svg>
                                                                                </button>
                                                                                <span
                                                                                    style="width:2rem;text-align:center;font-weight:500;">
                                                                                    <%= item.getQuantity() %>
                                                                                </span>
                                                                                <button type="button"
                                                                                    style="padding:0.5rem;"
                                                                                    onclick="cartAction('${pageContext.request.contextPath}/addQuantity', '<%= item.getCartID() %>', '1')">
                                                                                    <svg width="16" height="16"
                                                                                        fill="none"
                                                                                        stroke="currentColor"
                                                                                        stroke-width="2"
                                                                                        viewBox="0 0 24 24">
                                                                                        <line x1="12" x2="12" y1="5"
                                                                                            y2="19" />
                                                                                        <line x1="5" x2="19" y1="12"
                                                                                            y2="12" />
                                                                                    </svg>
                                                                                </button>
                                                                            </div>
                                                                        </div>
                                                                        <button type="button" class="remove-btn"
                                                                            onclick="cartAction('${pageContext.request.contextPath}/removeCart', '<%= item.getCartID() %>', '')">
                                                                            <svg width="16" height="16" fill="none"
                                                                                stroke="currentColor" stroke-width="2"
                                                                                viewBox="0 0 24 24">
                                                                                <polyline points="3 6 5 6 21 6" />
                                                                                <path
                                                                                    d="M19 6v14a2 2 0 0 1-2 2H7a2 2 0 0 1-2-2V6m3 0V4a2 2 0 0 1 2-2h4a2 2 0 0 1 2 2v2" />
                                                                            </svg>
                                                                            Remove
                                                                        </button>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                            <% } %>
                                                    </div>
                                                    <div class="mt-6 text-center">
                                                        <a href="${pageContext.request.contextPath}/index"
                                                            class="text-emerald-600 font-medium"
                                                            style="display:inline-flex;align-items:center;gap:0.25rem;">+
                                                            Add more items</a>
                                                    </div>
                                                </div>

                                                <div class="cart-summary">
                                                    <div class="summary-card">
                                                        <h2 class="text-xl font-bold mb-6">Order Summary</h2>
                                                        <div class="summary-row">
                                                            <span>Subtotal (<span id="cartItemCount">
                                                                    <%= cartItems.size() %>
                                                                </span> items)</span>
                                                            <span id="cartSubtotal">RM <%= String.format("%.2f",
                                                                    subtotalAmt) %></span>
                                                        </div>
                                                        <div class="summary-row">
                                                            <span>SST (6%)</span>
                                                            <span id="cartSst">RM <%= String.format("%.2f", sstAmt) %>
                                                            </span>
                                                        </div>
                                                        <div class="summary-row"><span>Pickup
                                                                Fee</span><span>Free</span></div>
                                                                
                                                        <!-- Points Toggle Feature -->
                                                        <style>
                                                            .pt-toggle-slider { position: absolute; cursor: pointer; top: 0; left: 0; right: 0; bottom: 0; background-color: #D1D5DB; transition: .4s; border-radius: 34px; }
                                                            .pt-toggle-slider:before { position: absolute; content: ""; height: 18px; width: 18px; left: 3px; bottom: 3px; background-color: white; transition: .4s; border-radius: 50%; }
                                                            input:checked + .pt-toggle-slider { background-color: #059669; }
                                                            input:checked + .pt-toggle-slider:before { transform: translateX(20px); }
                                                        </style>
                                                        <div class="summary-row" style="border-top: 1px dashed #E5E7EB; padding-top: 1rem; margin-top: 0.5rem; align-items: center;">
                                                            <div class="flex items-center gap-2">
                                                                <svg width="20" height="20" fill="none" stroke="#059669" stroke-width="2" viewBox="0 0 24 24">
                                                                    <circle cx="12" cy="12" r="10" />
                                                                    <path d="M12 16v-4m0-4h.01" />
                                                                </svg>
                                                                <span style="font-weight: 500;">Use Points</span>
                                                                <span class="text-xs text-emerald-600 bg-emerald-50 px-2 py-1 rounded-full"><%= String.format("%.2f", userPoints) %> available</span>
                                                            </div>
                                                            <label style="position: relative; display: inline-block; width: 44px; height: 24px;">
                                                                <input type="checkbox" name="usepoints" id="usePointsToggle" value="true" onchange="updateCartTotals()" style="opacity: 0; width: 0; height: 0;" <%= (userPoints <= 0) ? "disabled" : "" %>>
                                                                <span class="pt-toggle-slider"></span>
                                                            </label>
                                                        </div>
                                                        <div class="summary-row text-emerald-600" id="pointsDiscountRow" style="display: none; font-size: 0.875rem;">
                                                            <span>Points Discount</span>
                                                            <span id="cartPointsDiscount">-RM 0.00</span>
                                                        </div>

                                                        <div class="summary-total" style="border-top: 2px solid #E5E7EB; padding-top: 1rem; margin-top: 1rem;">
                                                            <span class="label">Total</span>
                                                            <span class="amount" id="cartTotal">RM <%= String.format("%.2f", totalAmt) %></span>
                                                        </div>
                                                        <p class="text-xs text-gray-500 mt-1" style="text-align:right;">
                                                            Inclusive of 6% SST</p>
                                                        <button type="submit" id="checkoutBtn"
                                                            class="btn btn-emerald btn-block btn-lg mt-6"
                                                            style="border-radius:0.75rem;">
                                                            Checkout
                                                            <svg width="20" height="20" fill="none"
                                                                stroke="currentColor" stroke-width="2"
                                                                viewBox="0 0 24 24" style="margin-left:0.5rem;">
                                                                <path d="M5 12h14M12 5l7 7-7 7" />
                                                            </svg>
                                                        </button>
                                                        <div
                                                            class="flex items-center justify-center gap-2 mt-4 text-sm text-gray-500">
                                                            <svg width="16" height="16" fill="none" stroke="#059669"
                                                                stroke-width="2" viewBox="0 0 24 24">
                                                                <path d="M12 22s8-4 8-10V5l-8-3-8 3v7c0 6 8 10 8 10z" />
                                                            </svg>
                                                            Secure checkout
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </form>
                                        <% } else { %>
                                            <div class="text-center" style="padding:4rem 0;">
                                                <div
                                                    style="width:6rem;height:6rem;background:#F9FAFB;border-radius:50%;display:flex;align-items:center;justify-content:center;margin:0 auto 1.5rem;">
                                                    <svg width="40" height="40" fill="none" stroke="#9CA3AF"
                                                        stroke-width="2" viewBox="0 0 24 24">
                                                        <path d="M6 2 3 6v14a2 2 0 0 0 2 2h14a2 2 0 0 0 2-2V6l-3-4z" />
                                                        <line x1="3" x2="21" y1="6" y2="6" />
                                                        <path d="M16 10a4 4 0 0 1-8 0" />
                                                    </svg>
                                                </div>
                                                <h2 class="text-xl font-bold mb-2">Your Cart is Empty</h2>
                                                <p class="text-gray-500 mb-8">Start exploring our menu to find your
                                                    perfect cup of tea.</p>
                                                <a href="${pageContext.request.contextPath}/index"
                                                    class="btn btn-emerald btn-lg">Explore Menu</a>
                                            </div>
                                            <% } %>
                        </main>

                        <!-- Hidden form for cart actions (minus/plus/remove) - OUTSIDE checkout form -->
                        <form id="cartActionForm" method="post" style="display:none;">
                            <input type="hidden" name="cartID" id="cartActionID">
                            <input type="hidden" name="quantity" id="cartActionQty">
                        </form>

                        <script>
                            function cartAction(url, cartID, quantity) {
                                var form = document.getElementById('cartActionForm');
                                form.action = url;
                                document.getElementById('cartActionID').value = cartID;
                                document.getElementById('cartActionQty').value = quantity;
                                form.submit();
                            }
                            function updateCartTotals() {
                                var checkboxes = document.querySelectorAll('input[name="cardIDs"]');
                                var totalAmt = 0;
                                var selectedCount = 0;

                                checkboxes.forEach(function (cb) {
                                    if (cb.checked) {
                                        var price = parseFloat(cb.getAttribute('data-price')) || 0;
                                        totalAmt += price;
                                        selectedCount++;
                                    }
                                });

                                var availablePoints = Number("<%= userPoints %>");
                                var usePointsToggle = document.getElementById('usePointsToggle');
                                var pointsDiscountRow = document.getElementById('pointsDiscountRow');
                                var discountAmt = 0;

                                if (usePointsToggle && usePointsToggle.checked) {
                                    discountAmt = Math.min(totalAmt, availablePoints);
                                    pointsDiscountRow.style.display = 'flex';
                                    pointsDiscountRow.style.justifyContent = 'space-between';
                                    document.getElementById('cartPointsDiscount').innerText = '-RM ' + discountAmt.toFixed(2);
                                } else {
                                    pointsDiscountRow.style.display = 'none';
                                }

                                var sstAmt = Math.round((totalAmt / 1.06 * 0.06) * 100) / 100;
                                var subtotalAmt = Math.round((totalAmt - sstAmt) * 100) / 100;
                                var finalTotal = Math.max(0, totalAmt - discountAmt);

                                document.getElementById('cartItemCount').innerText = selectedCount;
                                document.getElementById('cartSubtotal').innerText = 'RM ' + subtotalAmt.toFixed(2);
                                document.getElementById('cartSst').innerText = 'RM ' + sstAmt.toFixed(2);
                                document.getElementById('cartTotal').innerText = 'RM ' + finalTotal.toFixed(2);

                                var checkoutBtn = document.getElementById('checkoutBtn');
                                if (selectedCount === 0) {
                                    checkoutBtn.disabled = true;
                                    checkoutBtn.style.opacity = '0.5';
                                    checkoutBtn.style.cursor = 'not-allowed';
                                } else {
                                    checkoutBtn.disabled = false;
                                    checkoutBtn.style.opacity = '1';
                                    checkoutBtn.style.cursor = 'pointer';
                                }
                            }

                            // Initialize totals on page load
                            window.onload = function () {
                                updateCartTotals();
                            };
                        </script>

                        <%@ include file="Layout/footer.jsp" %>