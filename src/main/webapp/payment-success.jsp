<%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <%@ include file="Layout/header.jsp" %>

        <main class="success-page">
            <div class="success-card animate-in">
                <div class="success-icon">
                    <svg fill="none" stroke="currentColor" stroke-width="2" viewBox="0 0 24 24">
                        <path d="M22 11.08V12a10 10 0 1 1-5.93-9.14" />
                        <path d="M22 4 12 14.01l-3-3" />
                    </svg>
                </div>
                <h2 class="font-serif text-3xl font-bold mb-2" style="position:relative;z-index:1;">Payment Successful!
                </h2>
                <p class="text-gray-500 mb-8" style="position:relative;z-index:1;">Thank you for your order. We are
                    preparing your delicious drinks.</p>
                <div
                    style="background:#F9FAFB;border-radius:1rem;padding:1.5rem;margin-bottom:2rem;text-align:left;border:1px solid #F3F4F6;position:relative;z-index:1;">
                    <h3 class="text-sm font-medium text-gray-500"
                        style="text-transform:uppercase;letter-spacing:0.05em;margin-bottom:1rem;">Order Details</h3>
                    <div class="flex justify-between mb-2"><span class="text-gray-600">Status</span><span
                            class="font-medium text-gray-900">Preparing</span></div>
                    <div class="flex justify-between mb-2"><span class="text-gray-600">Payment Method</span><span
                            class="text-gray-900">Online Payment</span></div>
                </div>
                <div style="display:flex;flex-direction:column;gap:1rem;position:relative;z-index:1;">
                    <a href="${pageContext.request.contextPath}/orderList" class="btn btn-emerald btn-block"
                        style="border-radius:0.75rem;padding:1rem;">
                        View Orders
                        <svg width="20" height="20" fill="none" stroke="currentColor" stroke-width="2"
                            viewBox="0 0 24 24" style="margin-left:0.5rem;">
                            <path d="M5 12h14M12 5l7 7-7 7" />
                        </svg>
                    </a>
                    <a href="${pageContext.request.contextPath}/index" class="btn btn-outline-emerald btn-block"
                        style="border-radius:0.75rem;padding:1rem;border-width:2px;">
                        <svg width="20" height="20" fill="none" stroke="currentColor" stroke-width="2"
                            viewBox="0 0 24 24" style="margin-right:0.5rem;">
                            <path d="M6 2 3 6v14a2 2 0 0 0 2 2h14a2 2 0 0 0 2-2V6l-3-4z" />
                            <line x1="3" x2="21" y1="6" y2="6" />
                            <path d="M16 10a4 4 0 0 1-8 0" />
                        </svg>
                        Continue Shopping
                    </a>
                </div>
            </div>
        </main>

        <%@ include file="Layout/footer.jsp" %>