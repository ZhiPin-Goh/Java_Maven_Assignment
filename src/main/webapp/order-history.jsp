<%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <%@ page import="java.util.List" %>
        <%@ page import="ModelsDTO.TransactionDTO" %>
            <%@ page import="ModelsDTO.TransactionItemsDTO" %>
                <%@ include file="Layout/header.jsp" %>
                    <!-- @formatter:off -->
                    <% List<TransactionDTO> orderList = (List<TransactionDTO>) request.getAttribute("orderList");
                       Integer currentPageObj = (Integer) request.getAttribute("currentPage");
                       Integer totalPagesObj = (Integer) request.getAttribute("totalPages");
                       int currentPage = (currentPageObj != null) ? currentPageObj : 1;
                       int totalPages = (totalPagesObj != null) ? totalPagesObj : 1;
                    %>
                            <!-- @formatter:on -->

                            <main class="container py-12">
                                <h1 class="font-serif text-3xl font-bold mb-8">My Account</h1>
                                <div class="account-layout">
                                    <%@ include file="Layout/account-sidebar.jsp" %>
                                        <div class="account-content">
                                            <div class="card" style="border-radius:1rem;padding:2rem;">
                                                <div class="flex justify-between items-center mb-6">
                                                    <h2 class="text-xl font-bold">Order History</h2>
                                                    <% if (orderList !=null && !orderList.isEmpty()) { %>
                                                        <span class="text-sm text-gray-500">
                                                            <%= orderList.size() %> order(s)
                                                        </span>
                                                        <% } %>
                                                </div>

                                                <% if (orderList !=null && !orderList.isEmpty()) { %>
                                                    <div style="display:flex;flex-direction:column;gap:1rem;">
                                                        <!-- @formatter:off -->
                                                        <% for (TransactionDTO order : orderList) { String
                                                            statusClass="Completed" .equals(order.getStatus()) ? "ready"
                                                            : "preparing" ; List<TransactionItemsDTO> items =
                                                            order.getItems();
                                                            int showCount = (items != null) ? Math.min(items.size(), 3)
                                                            : 0;
                                                            %>
                                                            <!-- @formatter:on -->
                                                            <div style="border:1px solid #F3F4F6;border-radius:0.75rem;padding:1.5rem;transition:all 0.2s;"
                                                                onmouseover="this.style.boxShadow='0 4px 12px rgba(0,0,0,0.08)'"
                                                                onmouseout="this.style.boxShadow='none'">
                                                                <div class="flex justify-between items-center mb-4">
                                                                    <div>
                                                                        <span class="text-sm text-gray-500">Order
                                                                            #</span>
                                                                        <span class="font-bold text-gray-900">
                                                                            <%= order.getTransactionNo() %>
                                                                        </span>
                                                                    </div>
                                                                    <span class="status-badge <%= statusClass %>">
                                                                        <%= order.getStatus() %>
                                                                    </span>
                                                                </div>
                                                                <div class="flex justify-between items-center mb-4">
                                                                    <span class="text-sm text-gray-500">
                                                                        <%= order.getOrderTime() %>
                                                                    </span>
                                                                    <span class="font-bold text-emerald-700">RM <%=
                                                                            order.getTotalAmount() %></span>
                                                                </div>
                                                                <% if (items !=null && !items.isEmpty()) { %>
                                                                    <div
                                                                        style="display:flex;gap:0.5rem;flex-wrap:wrap;margin-bottom:1rem;">
                                                                        <% for (int i=0; i < showCount; i++) { %>
                                                                            <div
                                                                                style="display:flex;align-items:center;gap:0.5rem;background:#F9FAFB;padding:0.25rem 0.75rem;border-radius:0.5rem;">
                                                                                <span class="text-sm text-gray-700">
                                                                                    <%= items.get(i).getBeverageName()
                                                                                        %>
                                                                                </span>
                                                                                <span class="text-xs text-gray-400">x<%=
                                                                                        items.get(i).getQuantity() %>
                                                                                        </span>
                                                                            </div>
                                                                            <% } %>
                                                                                <% if (items.size()> 3) { %>
                                                                                    <span
                                                                                        class="text-sm text-gray-400">+
                                                                                        <%= items.size() - 3 %>
                                                                                            more</span>
                                                                                    <% } %>
                                                                    </div>
                                                                    <% } %>
                                                                        <div
                                                                            style="border-top:1px solid #F3F4F6;padding-top:1rem;">
                                                                            <a href="${pageContext.request.contextPath}/orderDetails?transactionNo=<%= order.getTransactionNo() %>"
                                                                                class="btn btn-sm btn-outline-emerald"
                                                                                style="border-radius:0.5rem;">View
                                                                                Details</a>
                                                                        </div>
                                                            </div>
                                                            <% } %>
                                                    </div>

                                                    <!-- Pagination Controls -->
                                                    <% if (totalPages > 1) { %>
                                                        <div style="display: flex; justify-content: space-between; align-items: center; margin-top: 2rem; border-top: 1px solid #E5E7EB; padding-top: 1rem;">
                                                            <% if (currentPage > 1) { %>
                                                                <a href="${pageContext.request.contextPath}/orderList?page=<%= currentPage - 1 %>"
                                                                   class="btn btn-outline-emerald" style="border-radius:0.5rem; text-decoration: none;">
                                                                    &larr; Previous
                                                                </a>
                                                            <% } else { %>
                                                                <div></div>
                                                            <% } %>

                                                            <span class="text-sm text-gray-500">
                                                                Page <%= currentPage %> of <%= totalPages %>
                                                            </span>

                                                            <% if (currentPage < totalPages) { %>
                                                                <a href="${pageContext.request.contextPath}/orderList?page=<%= currentPage + 1 %>"
                                                                   class="btn btn-outline-emerald" style="border-radius:0.5rem; text-decoration: none;">
                                                                    Next &rarr;
                                                                </a>
                                                            <% } else { %>
                                                                <div></div>
                                                            <% } %>
                                                        </div>
                                                    <% } %>

                                                    <% } else { %>
                                                        <div class="text-center" style="padding:3rem 0;">
                                                            <p class="text-gray-500 mb-4">No orders yet.</p>
                                                            <a href="${pageContext.request.contextPath}/index"
                                                                class="btn btn-emerald">Start Ordering</a>
                                                        </div>
                                                        <% } %>
                                            </div>
                                        </div>
                                </div>
                            </main>

                            <%@ include file="Layout/footer.jsp" %>