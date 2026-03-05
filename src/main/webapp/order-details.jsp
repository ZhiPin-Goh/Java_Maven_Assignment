<%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <%@ page import="java.util.List" %>
        <%@ page import="ModelsDTO.TransactionDTO" %>
            <%@ page import="ModelsDTO.TransactionItemsDTO" %>
                <%@ include file="Layout/header.jsp" %>
                    <!-- @formatter:off -->
                    <% TransactionDTO orderDetails = (TransactionDTO) request.getAttribute("orderDetails");
                           if (orderDetails == null) {
                               response.sendRedirect("orderList");
                               return;
                           }
                           List<TransactionItemsDTO> items = orderDetails.getItems();
                           String status = orderDetails.getStatus();
                           boolean isPlaced = true;
                           boolean isPreparing = "Preparing".equals(status) || "Ready".equals(status) || "Ready for Pickup".equals(status) || "Completed".equals(status);
                           boolean isReady = "Ready".equals(status) || "Ready for Pickup".equals(status) || "Completed".equals(status);
                           boolean isCompleted = "Completed".equals(status);
                        %>
                        <!-- @formatter:on -->

                        <main class="container py-12">
                            <div class="mb-8">
                                <a href="${pageContext.request.contextPath}/orderList"
                                    class="text-sm font-medium text-emerald-600"
                                    style="display:inline-flex;align-items:center;gap:0.25rem;">&larr; Back to
                                    Orders</a>
                            </div>
                            <div class="flex gap-8" style="flex-wrap:wrap;">
                                <!-- Order Tracking Timeline -->
                                <div style="flex:1;min-width:300px;">
                                    <div class="card" style="border-radius:1rem;padding:2rem;">
                                        <div class="flex justify-between items-center mb-6">
                                            <h2 class="text-xl font-bold">Order #<%= orderDetails.getTransactionNo() %>
                                            </h2>
                                            <span class="status-badge <%= isCompleted ? " ready" : "preparing" %>"><%=
                                                    status %></span>
                                        </div>
                                        <div class="mb-4">
                                            <span class="text-sm text-gray-500">
                                                <%= orderDetails.getOrderTime() %>
                                            </span>
                                            <% if (orderDetails.getPickupCode() !=null &&
                                                !"N/A".equals(orderDetails.getPickupCode())) { %>
                                                <span
                                                    style="margin-left:1rem;background:#ECFDF5;color:#047857;padding:0.25rem 0.75rem;border-radius:0.25rem;font-size:0.875rem;font-weight:700;">Pickup:
                                                    <%= orderDetails.getPickupCode() %></span>
                                                <% } %>
                                        </div>
                                        <div class="tracking-timeline" style="margin-top:2rem;">
                                            <div class="tracking-step">
                                                <div class="tracking-dot completed">
                                                    <svg width="12" height="12" fill="#fff" viewBox="0 0 24 24">
                                                        <path d="M22 4L12 14.01l-3-3" stroke="#fff" stroke-width="3"
                                                            fill="none" />
                                                    </svg>
                                                </div>
                                                <div>
                                                    <h3 class="font-bold text-gray-900">Order Placed</h3>
                                                    <p class="text-sm text-gray-500">
                                                        <%= orderDetails.getOrderTime() %>
                                                    </p>
                                                </div>
                                            </div>
                                            <div class="tracking-step <%= !isPreparing ? " pending" : "" %>">
                                                <div class="tracking-dot <%= isPreparing && !isReady ? " active" :
                                                    isPreparing ? "completed" : "pending" %>">
                                                    <% if (isPreparing) { %><svg width="12" height="12" fill="#fff"
                                                            viewBox="0 0 24 24">
                                                            <path d="M22 4L12 14.01l-3-3" stroke="#fff" stroke-width="3"
                                                                fill="none" />
                                                        </svg>
                                                        <% } %>
                                                </div>
                                                <div>
                                                    <h3 class="font-bold">Preparing</h3>
                                                    <p class="text-sm text-gray-500">Your drinks are being prepared</p>
                                                </div>
                                            </div>
                                            <div class="tracking-step <%= !isReady ? " pending" : "" %>">
                                                <div class="tracking-dot <%= isReady && !isCompleted ? " active" :
                                                    isReady ? "completed" : "pending" %>">
                                                    <% if (isReady) { %><svg width="12" height="12" fill="#fff"
                                                            viewBox="0 0 24 24">
                                                            <path d="M22 4L12 14.01l-3-3" stroke="#fff" stroke-width="3"
                                                                fill="none" />
                                                        </svg>
                                                        <% } %>
                                                </div>
                                                <div>
                                                    <h3 class="font-bold">Ready for Pickup</h3>
                                                    <p class="text-sm text-gray-500">Your order is ready</p>
                                                </div>
                                            </div>
                                            <div class="tracking-step <%= !isCompleted ? " pending" : "" %>">
                                                <div class="tracking-dot <%= isCompleted ? " completed" : "pending" %>">
                                                    <% if (isCompleted) { %><svg width="12" height="12" fill="#fff"
                                                            viewBox="0 0 24 24">
                                                            <path d="M22 4L12 14.01l-3-3" stroke="#fff" stroke-width="3"
                                                                fill="none" />
                                                        </svg>
                                                        <% } %>
                                                </div>
                                                <div>
                                                    <h3 class="font-bold">Completed</h3>
                                                    <p class="text-sm text-gray-500">Order complete</p>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <!-- Order Items -->
                                <div style="flex:1;min-width:300px;">
                                    <div class="card" style="border-radius:1rem;padding:2rem;">
                                        <h2 class="text-xl font-bold mb-6">Order Items</h2>
                                        <% if (items !=null) { for (TransactionItemsDTO item : items) { %>
                                            <div
                                                style="display:flex;gap:1rem;padding:1rem 0;border-bottom:1px solid #F3F4F6;align-items:center;">
                                                <div
                                                    style="width:4rem;height:4rem;border-radius:0.5rem;overflow:hidden;flex-shrink:0;background:#F3F4F6;">
                                                    <img src="http://localhost:5018<%= item.getImagePath() %>"
                                                        alt="<%= item.getBeverageName() %>"
                                                        style="width:100%;height:100%;object-fit:cover;">
                                                </div>
                                                <div style="flex:1;">
                                                    <h3 class="font-bold">
                                                        <%= item.getBeverageName() %>
                                                    </h3>
                                                    <p class="text-sm text-gray-500">
                                                        <%= item.getDescription() %>
                                                    </p>
                                                </div>
                                                <div class="text-right">
                                                    <span class="text-sm text-gray-500">x<%= item.getQuantity() %>
                                                            </span>
                                                    <p class="font-bold text-emerald-700 mt-1">RM <%=
                                                            String.format("%.2f", item.getPrice().doubleValue()) %>
                                                    </p>
                                                </div>
                                            </div>
                                            <% } } %>
                                                <div class="summary-total mt-4"
                                                    style="padding-top:1rem;border-top:2px solid #F3F4F6;">
                                                    <span class="label">Total</span>
                                                    <span class="amount">RM <%= String.format("%.2f",
                                                            orderDetails.getTotalAmount().doubleValue()) %></span>
                                                </div>
                                    </div>
                                </div>
                            </div>
                        </main>

                        <%@ include file="Layout/footer.jsp" %>