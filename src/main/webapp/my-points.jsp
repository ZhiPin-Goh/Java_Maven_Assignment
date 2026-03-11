<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="Models.User" %>
<%@ page import="Models.PointLogs" %>
<%@ page import="java.util.List" %>
<%@ page import="java.math.BigDecimal" %>
<%@ include file="Layout/header.jsp" %>
<% 
    if (session.getAttribute("loggedInUserID") == null) { 
        response.sendRedirect("index"); 
        return;
    } 
    BigDecimal points = (BigDecimal) request.getAttribute("points");
    List<PointLogs> pointsLogs = (List<PointLogs>) request.getAttribute("pointsLogs");
    
    Integer currentPage = (Integer) request.getAttribute("currentPage");
    if (currentPage == null) currentPage = 1;
    Integer totalPages = (Integer) request.getAttribute("totalPages");
    if (totalPages == null) totalPages = 1;

    Boolean alreadyCheckedIn = (Boolean) request.getAttribute("alreadyCheckedIn");
    if (alreadyCheckedIn == null) {
        alreadyCheckedIn = false;
    }
%>

<main class="container py-12">
    <h1 class="font-serif text-3xl font-bold mb-8">My Account</h1>
    <div class="account-layout">
        <%@ include file="Layout/account-sidebar.jsp" %>
        <div class="account-content">
            <div class="card" style="border-radius:1rem;padding:2rem;margin-bottom:2rem;">
                <h2 class="text-xl font-bold mb-6">My Points</h2>
                
                <% if (request.getAttribute("errorMessage") != null) { %>
                    <div style="color: #DC2626; margin-bottom: 1rem; padding: 1rem; background: #FEE2E2; border-radius: 0.5rem;">
                        <%= request.getAttribute("errorMessage") %>
                    </div>
                <% } %>

                <div class="flex items-center justify-between" style="background: #F3F4F6; padding: 2rem; border-radius: 0.75rem;">
                    <div>
                        <p class="text-gray-500 mb-1">Current Balance</p>
                        <h3 class="text-4xl font-bold text-emerald-600">
                            <%= points != null ? String.format("%.2f", points) : "0.00" %> <span class="text-lg text-gray-500">Pts</span>
                        </h3>
                    </div>
                    <div>
                        <form action="${pageContext.request.contextPath}/getPoints" method="post" id="checkInForm">
                            <% if (alreadyCheckedIn) { %>
                                <button type="button" class="btn btn-emerald" style="border-radius:0.75rem;" onclick="document.getElementById('checkinModal').style.display='flex'">Daily Check-In</button>
                            <% } else { %>
                                <button type="submit" class="btn btn-emerald" style="border-radius:0.75rem;">Daily Check-In</button>
                            <% } %>
                        </form>
                    </div>
                </div>
            </div>

            <div class="card" style="border-radius:1rem;padding:2rem;">
                <h3 class="text-lg font-bold mb-4">Point Transactions</h3>
                <% if (pointsLogs != null && !pointsLogs.isEmpty()) { %>
                    <div style="overflow-x: auto;">
                        <table style="width: 100%; border-collapse: collapse; text-align: left;">
                            <thead>
                                <tr style="border-bottom: 2px solid #E5E7EB;">
                                    <th style="padding: 1rem 0; color: #6B7280; font-weight: 600;">Date</th>
                                    <th style="padding: 1rem 0; color: #6B7280; font-weight: 600;">Description</th>
                                    <th style="padding: 1rem 0; color: #6B7280; font-weight: 600; text-align: right;">Amount</th>
                                </tr>
                            </thead>
                            <tbody>
                                <% for(PointLogs log : pointsLogs) { %>
                                    <tr style="border-bottom: 1px solid #F3F4F6;">
                                        <td style="padding: 1rem 0; color: #374151; white-space: nowrap;">
                                            <%
                                                String dateStr = log.getCreateAt();
                                                if (dateStr != null && dateStr.contains("T")) {
                                                    dateStr = dateStr.replace("T", " ");
                                                    if (dateStr.contains(".")) {
                                                        dateStr = dateStr.substring(0, dateStr.indexOf("."));
                                                    }
                                                    out.print(dateStr);
                                                } else {
                                                    out.print(dateStr != null ? dateStr : "N/A");
                                                }
                                            %>
                                        </td>
                                        <td style="padding: 1rem 0; color: #374151;"><%= log.getDescription() != null ? log.getDescription() : "N/A" %></td>
                                        <td style="padding: 1rem 0; text-align: right; font-weight: 600; color: <%= log.getAmount() != null && log.getAmount().compareTo(BigDecimal.ZERO) > 0 ? "#059669" : "#DC2626" %>;">
                                            <% if(log.getAmount() != null) { %>
                                                <%= log.getAmount().compareTo(BigDecimal.ZERO) > 0 ? "+" : "" %><%= String.format("%.2f", log.getAmount()) %>
                                            <% } else { %>
                                                0.00
                                            <% } %>
                                        </td>
                                    </tr>
                                <% } %>
                            </tbody>
                        </table>
                    </div>
                    
                    <% if (totalPages > 1) { %>
                        <div class="flex justify-between items-center mt-6 pt-4 border-t border-gray-100">
                            <% if (currentPage > 1) { %>
                                <a href="${pageContext.request.contextPath}/myPoints?page=<%= currentPage - 1 %>" 
                                   class="btn btn-sm btn-outline" style="border-radius:0.5rem; padding: 0.5rem 1rem; color: #4B5563; border: 1px solid #D1D5DB; text-decoration: none;">
                                   &larr; Previous
                                </a>
                            <% } else { %>
                                <div></div>
                            <% } %>
                            
                            <span class="text-sm text-gray-500">Page <%= currentPage %> of <%= totalPages %></span>

                            <% if (currentPage < totalPages) { %>
                                <a href="${pageContext.request.contextPath}/myPoints?page=<%= currentPage + 1 %>" 
                                   class="btn btn-sm btn-outline" style="border-radius:0.5rem; padding: 0.5rem 1rem; color: #4B5563; border: 1px solid #D1D5DB; text-decoration: none;">
                                   Next &rarr;
                                </a>
                            <% } else { %>
                                <div></div>
                            <% } %>
                        </div>
                    <% } %>

                <% } else { %>
                    <p class="text-gray-500 text-center py-4">No point logs available.</p>
                <% } %>
            </div>
        </div>
    </div>
    <!-- Custom Check-in Modal -->
    <div id="checkinModal" style="display:none; position:fixed; top:0; left:0; width:100%; height:100%; background:rgba(0,0,0,0.5); z-index:50; justify-content:center; align-items:center;">
        <div style="background:white; padding:2rem; border-radius:1rem; max-width:400px; width:90%; text-align:center; box-shadow:0 10px 25px rgba(0,0,0,0.1); animation: modalFadeIn 0.3s ease-out forwards;">
            <div style="background:#FEF3C7; color:#D97706; width:48px; height:48px; border-radius:50%; display:flex; justify-content:center; align-items:center; margin:0 auto 1rem;">
                <svg width="24" height="24" fill="none" stroke="currentColor" stroke-width="2" viewBox="0 0 24 24">
                    <path stroke-linecap="round" stroke-linejoin="round" d="M12 9v2m0 4h.01m-6.938 4h13.856c1.54 0 2.502-1.667 1.732-3L13.732 4c-.77-1.333-2.694-1.333-3.464 0L3.34 16c-.77 1.333.192 3 1.732 3z" />
                </svg>
            </div>
            <h3 class="text-xl font-bold text-gray-900 mb-2">Already Checked In!</h3>
            <p class="text-gray-500 mb-6" style="line-height:1.5;">You have already collected your daily points today. Please come back tomorrow after 12:00 AM for more rewards!</p>
            <button type="button" onclick="document.getElementById('checkinModal').style.display='none'" class="btn btn-emerald" style="width:100%; border-radius:0.5rem; padding:0.75rem;">Got it, thanks!</button>
        </div>
    </div>
    
    <style>
        @keyframes modalFadeIn {
            from { opacity: 0; transform: translateY(-20px); }
            to { opacity: 1; transform: translateY(0); }
        }
    </style>
</main>

<%@ include file="Layout/footer.jsp" %>
