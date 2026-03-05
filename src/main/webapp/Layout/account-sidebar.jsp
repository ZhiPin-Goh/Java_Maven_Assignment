<%@ page import="Models.User" %>
    <%@ page import="Services.UserServices" %>
        <!-- @formatter:off -->
        <% String acPath=request.getServletPath(); User sidebarUser=(User) request.getAttribute("user"); if
            (sidebarUser==null) { Integer sidebarUid=(Integer) session.getAttribute("loggedInUserID"); if (sidebarUid
            !=null) { try { UserServices sidebarSvc=new UserServices();
            sidebarUser=sidebarSvc.SearchUserByID(sidebarUid); } catch (Exception e) { sidebarUser=null; } } } String
            displayName=(sidebarUser !=null) ? sidebarUser.getUserName() : "User" ; String displayEmail=(sidebarUser
            !=null) ? sidebarUser.getEmail() : "" ; String initials="" ; if (displayName !=null &&
            !displayName.isEmpty()) { String[] parts=displayName.split(" ");
    initials = String.valueOf(parts[0].charAt(0));
    if (parts.length > 1) initials += String.valueOf(parts[parts.length - 1].charAt(0));
    initials = initials.toUpperCase();
}
String profileActive = acPath.contains(" profile") ? "active" : "" ; String orderActive=acPath.contains("order")
            ? "active" : "" ; String securityActive=acPath.contains("change") ? "active" : "" ; %>
            <!-- @formatter:on -->
            <div class="account-sidebar">
                <div class="account-sidebar-card">
                    <div class="account-avatar">
                        <div class="account-avatar-circle">
                            <%= initials %>
                        </div>
                        <h2 style="font-size:1.25rem;font-weight:700;color:#111827;">
                            <%= displayName %>
                        </h2>
                        <p class="text-sm text-gray-500">
                            <%= displayEmail %>
                        </p>
                    </div>
                    <nav class="account-nav">
                        <a href="${pageContext.request.contextPath}/profile" class="<%= profileActive %>">
                            <svg width="20" height="20" fill="none" stroke="currentColor" stroke-width="2"
                                viewBox="0 0 24 24">
                                <path d="M20 21v-2a4 4 0 0 0-4-4H8a4 4 0 0 0-4 4v2" />
                                <circle cx="12" cy="7" r="4" />
                            </svg>
                            Profile Information
                        </a>
                        <a href="${pageContext.request.contextPath}/orderList" class="<%= orderActive %>">
                            <svg width="20" height="20" fill="none" stroke="currentColor" stroke-width="2"
                                viewBox="0 0 24 24">
                                <path d="M6 2 3 6v14a2 2 0 0 0 2 2h14a2 2 0 0 0 2-2V6l-3-4z" />
                                <line x1="3" x2="21" y1="6" y2="6" />
                                <path d="M16 10a4 4 0 0 1-8 0" />
                            </svg>
                            Order History
                        </a>
                        <a href="${pageContext.request.contextPath}/change-password.jsp" class="<%= securityActive %>">
                            <svg width="20" height="20" fill="none" stroke="currentColor" stroke-width="2"
                                viewBox="0 0 24 24">
                                <path d="M12 22s8-4 8-10V5l-8-3-8 3v7c0 6 8 10 8 10z" />
                            </svg>
                            Security Settings
                        </a>
                        <a href="${pageContext.request.contextPath}/logout" class="signout">
                            <svg width="20" height="20" fill="none" stroke="currentColor" stroke-width="2"
                                viewBox="0 0 24 24">
                                <path d="M9 21H5a2 2 0 0 1-2-2V5a2 2 0 0 1 2-2h4" />
                                <polyline points="16 17 21 12 16 7" />
                                <line x1="21" x2="9" y1="12" y2="12" />
                            </svg>
                            Sign Out
                        </a>
                    </nav>
                </div>
            </div>