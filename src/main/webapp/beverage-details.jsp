<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="Models.Beverage" %>
<%@ page import="java.util.List" %>
<%@ page import="Models.Sizes" %>
<%@ page import="Models.SugarLevels" %>
<%@ page import="Models.IceLevels" %>
<%@ include file="Layout/header.jsp" %>

<%
    Beverage beverage = (Beverage) request.getAttribute("beverage");
    if (beverage == null) {
        response.sendRedirect("index");
        return;
    }

    // 拿取 Controller 传过来的动态选项数据
    List<Sizes> sizes = (List<Sizes>) request.getAttribute("sizes");
    List<SugarLevels> sugars = (List<SugarLevels>) request.getAttribute("sugars");
    List<IceLevels> ices = (List<IceLevels>) request.getAttribute("ices");

    String imgBase = "http://localhost:5018" ;
    String hotSelectedClass = !beverage.isHasIceOption() ? "selected" : "" ;
    String hotChecked = !beverage.isHasIceOption() ? "checked" : "" ;
%>

<main class="container py-12">
    <nav class="flex text-sm text-gray-500 mb-8" style="gap:0.25rem;">
        <a href="${pageContext.request.contextPath}/index" style="color:#059669;">Menu</a>
        <span>&rsaquo;</span>
        <% if (beverage.getBeverageCategory() !=null && !beverage.getBeverageCategory().equals("N/A")) { %>
            <span><%= beverage.getBeverageCategory() %></span>
            <span>&rsaquo;</span>
        <% } %>
        <span class="text-gray-900 font-medium"><%= beverage.getBeverageName() %></span>
    </nav>

    <div class="flex gap-12" style="flex-wrap:wrap;">
        <div style="flex:1;min-width:300px;">
            <div style="background:#ECFDF5;border-radius:1.5rem;padding:2rem;position:relative;overflow:hidden;">
                <% if (beverage.getBeverageCategory() !=null && !beverage.getBeverageCategory().equals("N/A")) { %>
                    <div class="card-tag"><%= beverage.getBeverageCategory() %></div>
                <% } %>
                <img src="<%= beverage.getBeverageImagePath() %>"
                     alt="<%= beverage.getBeverageName() %>"
                     style="width:100%;max-width:28rem;margin:0 auto;border-radius:1rem;box-shadow:0 25px 50px rgba(0,0,0,0.15);transition:transform 0.5s;"
                     onmouseover="this.style.transform='scale(1.05)'"
                     onmouseout="this.style.transform='scale(1)'">
            </div>
        </div>

        <div style="flex:1;min-width:300px;">
            <h1 class="font-serif text-3xl font-bold mb-2"><%= beverage.getBeverageName() %></h1>
            <div class="flex items-center gap-4 mb-4">
                <span class="text-2xl font-bold text-emerald-700">RM<%= String.format("%.2f", beverage.getPrice()) %></span>
            </div>
            <p class="text-gray-600 mb-8" style="line-height:1.7;"><%= beverage.getBeverageDescription() %></p>

            <form action="${pageContext.request.contextPath}/addToCart" method="post">
                <input type="hidden" name="beverageId" value="<%= beverage.getID() %>">

                <div class="option-group">
                    <h3>Size</h3>
                    <div class="option-buttons">
                        <% if (sizes != null && !sizes.isEmpty()) {
                            boolean isFirst = true;
                            for (Sizes s : sizes) {
                                String checked = isFirst ? "checked" : "";
                                String selectedClass = isFirst ? "selected" : "";
                                String priceText = (s.getPriceModifier() != null && s.getPriceModifier().doubleValue() > 0)
                                    ? " (+RM" + String.format("%.2f", s.getPriceModifier().doubleValue()) + ")"
                                    : "";
                        %>
                        <label class="option-btn <%= selectedClass %>" onclick="selectOption(this,'size')">
                            <input type="radio" name="sizeId" value="<%= s.getID() %>" <%= checked %> style="display:none;">
                            <span><%= s.getBeverageSize() %><%= priceText %></span>
                        </label>
                        <% isFirst = false; } } else { %>
                            <span style="color:red;font-size:0.8rem;">(Failed to load sizes)</span>
                        <% } %>
                    </div>
                </div>

                <div class="option-group">
                    <h3>Temperature</h3>
                    <div class="option-buttons">
                        <% if (beverage.isHasIceOption()) { %>
                            <label class="option-btn selected" onclick="selectOption(this,'temp')">
                                <input type="radio" name="tempOption" value="iced" checked style="display:none;">
                                <span>Iced</span>
                            </label>
                        <% } %>
                        <% if (beverage.isHasHotOption()) { %>
                            <label class="option-btn <%= hotSelectedClass %>" onclick="selectOption(this,'temp')">
                                <input type="radio" name="tempOption" value="hot" <%= hotChecked %> style="display:none;">
                                <span>Hot</span>
                            </label>
                        <% } %>
                    </div>
                </div>

                <div class="option-group">
                    <h3>Sweetness Level</h3>
                    <div class="option-grid">
                        <% if (sugars != null && !sugars.isEmpty()) {
                            boolean isFirst = true;
                            for (SugarLevels sl : sugars) {
                                String checked = isFirst ? "checked" : "";
                                String selectedClass = isFirst ? "selected" : "";
                        %>
                        <label class="option-btn <%= selectedClass %>" onclick="selectOption(this,'sugar')">
                            <input type="radio" name="sugarId" value="<%= sl.getID() %>" <%= checked %> style="display:none;">
                            <span><%= sl.getSugarOption() %></span>
                        </label>
                        <% isFirst = false; } } else { %>
                            <span style="color:red;font-size:0.8rem;">(Failed to load sweetness levels)</span>
                        <% } %>
                    </div>
                </div>

                <div class="option-group" id="iceLevelGroup">
                    <h3>Ice Level</h3>
                    <div class="option-grid">
                        <% if (ices != null && !ices.isEmpty()) {
                            boolean isFirst = true;
                            for (IceLevels ice : ices) {
                                String checked = isFirst ? "checked" : "";
                                String selectedClass = isFirst ? "selected" : "";
                        %>
                        <label class="option-btn <%= selectedClass %>" onclick="selectOption(this,'ice')">
                            <input type="radio" name="iceId" value="<%= ice.getID() %>" <%= checked %> style="display:none;">
                            <span><%= ice.getIceOption() %></span>
                        </label>
                        <% isFirst = false; } } else { %>
                            <span style="color:red;font-size:0.8rem;">(Failed to load ice levels)</span>
                        <% } %>
                    </div>
                </div>

                <div style="border-top:1px solid #E5E7EB;padding-top:1.5rem;margin-top:1rem;">
                    <div class="flex items-center gap-4">
                        <div class="cart-qty" style="border-radius:9999px;">
                            <button type="button" onclick="changeQty(-1)" style="padding:0.75rem;">
                                <svg width="20" height="20" fill="none" stroke="currentColor" stroke-width="2" viewBox="0 0 24 24">
                                    <line x1="5" x2="19" y1="12" y2="12" />
                                </svg>
                            </button>
                            <input type="number" name="quantity" id="quantity" value="1" min="1" max="20"
                                   style="width:3rem;text-align:center;border:none;font-weight:600;font-size:1rem;outline:none;">
                            <button type="button" onclick="changeQty(1)" style="padding:0.75rem;">
                                <svg width="20" height="20" fill="none" stroke="currentColor" stroke-width="2" viewBox="0 0 24 24">
                                    <line x1="12" x2="12" y1="5" y2="19" />
                                    <line x1="5" x2="19" y1="12" y2="12" />
                                </svg>
                            </button>
                        </div>
                        <button type="submit" class="btn btn-emerald btn-lg" style="flex:1;border-radius:9999px;font-size:1.125rem;padding:1rem;">
                            Add to Cart
                        </button>
                    </div>
                </div>
            </form>
        </div>
    </div>
</main>

<script>
    function selectOption(el, group) {
        el.closest('.option-buttons, .option-grid').querySelectorAll('.option-btn').forEach(function (b) { b.classList.remove('selected'); });
        el.classList.add('selected');
        el.querySelector('input').checked = true;
        if (group === 'temp') {
            var selectedVal = el.querySelector('input').value;
            var iceGroup = document.getElementById('iceLevelGroup');
            if (iceGroup) {
                if (selectedVal === 'hot') {
                    iceGroup.style.display = 'none';
                } else {
                    iceGroup.style.display = '';
                }
            }
        }
    }
    function changeQty(delta) {
        var input = document.getElementById('quantity');
        var val = parseInt(input.value) + delta;
        if (val >= 1 && val <= 20) input.value = val;
    }
    // On page load, hide ice level if hot is default selected
    (function () {
        var checkedTemp = document.querySelector('input[name="tempOption"]:checked');
        if (checkedTemp && checkedTemp.value === 'hot') {
            var iceGroup = document.getElementById('iceLevelGroup');
            if (iceGroup) iceGroup.style.display = 'none';
        }
    })();
</script>

<%@ include file="Layout/footer.jsp" %>