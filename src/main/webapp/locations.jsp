<%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <%@ include file="Layout/header.jsp" %>

        <main>
            <section class="location-hero">
                <div class="location-hero-bg">
                    <img src="https://images.unsplash.com/photo-1554118811-1e0d58224f24?ixlib=rb-4.0.3&auto=format&fit=crop&w=1200&q=80"
                        alt="Store Interior">
                    <div class="location-hero-overlay"></div>
                </div>
                <div class="container" style="position:relative;z-index:10;">
                    <h1 class="font-serif text-4xl font-bold animate-in">Our Location</h1>
                    <p class="mt-4 text-lg"
                        style="color:rgba(209,250,229,0.8);max-width:40rem;margin-left:auto;margin-right:auto;">Find
                        your zen at our flagship store. We can't wait to serve you.</p>
                </div>
            </section>

            <div class="container" style="margin-top:-3rem;position:relative;z-index:20;padding-bottom:5rem;">
                <div class="location-card animate-in">
                    <div class="location-map">
                        <iframe
                            src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d3983.6!2d101.6344!3d3.2098!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x6c3c54f0!2sJalan%20Rimbunan%20Raya%2C%20Kepong%2C%20Kuala%20Lumpur!5e0!3m2!1sen!2smy!4v1680000000000!5m2!1sen!2smy"
                            allowfullscreen="false" loading="lazy" referrerpolicy="no-referrer-when-downgrade"
                            title="Zen Tea Location Map"></iframe>
                    </div>
                    <div class="location-details">
                        <div
                            style="display:inline-block;background:#ECFDF5;color:#047857;padding:0.375rem 1rem;border-radius:9999px;font-size:0.875rem;font-weight:700;text-transform:uppercase;letter-spacing:0.05em;margin-bottom:1.5rem;">
                            Flagship Store</div>
                        <h2 class="font-serif text-3xl font-bold mb-4">Zen Tea - Flagship Store</h2>
                        <p class="text-gray-600 mb-8" style="line-height:1.7;">Visit our flagship store in the heart of
                            downtown. Experience our full menu of artisan teas, seasonal specials, and a peaceful
                            atmosphere designed for your comfort.</p>
                        <div class="location-info-item">
                            <div class="location-icon"><svg width="20" height="20" fill="none" stroke="currentColor"
                                    stroke-width="2" viewBox="0 0 24 24">
                                    <path d="M21 10c0 7-9 13-9 13s-9-6-9-13a9 9 0 0 1 18 0z" />
                                    <circle cx="12" cy="10" r="3" />
                                </svg></div>
                            <div>
                                <h3 class="text-sm font-bold mb-1">Address</h3><span class="text-gray-600">Jalan
                                    Rimbunan Raya, Kepong, 52100 Kuala Lumpur, Malaysia</span>
                            </div>
                        </div>
                        <div class="location-info-item">
                            <div class="location-icon"><svg width="20" height="20" fill="none" stroke="currentColor"
                                    stroke-width="2" viewBox="0 0 24 24">
                                    <circle cx="12" cy="12" r="10" />
                                    <polyline points="12 6 12 12 16 14" />
                                </svg></div>
                            <div>
                                <h3 class="text-sm font-bold mb-1">Opening Hours</h3><span
                                    class="text-gray-600">Mon-Sun: 8:00 AM - 9:00 PM</span>
                            </div>
                        </div>
                        <div class="location-info-item">
                            <div class="location-icon"><svg width="20" height="20" fill="none" stroke="currentColor"
                                    stroke-width="2" viewBox="0 0 24 24">
                                    <path
                                        d="M22 16.92v3a2 2 0 0 1-2.18 2 19.79 19.79 0 0 1-8.63-3.07 19.5 19.5 0 0 1-6-6 19.79 19.79 0 0 1-3.07-8.67A2 2 0 0 1 4.11 2h3a2 2 0 0 1 2 1.72c.127.96.361 1.903.7 2.81a2 2 0 0 1-.45 2.11L8.09 9.91a16 16 0 0 0 6 6l1.27-1.27a2 2 0 0 1 2.11-.45c.907.339 1.85.573 2.81.7A2 2 0 0 1 22 16.92z" />
                                </svg></div>
                            <div>
                                <h3 class="text-sm font-bold mb-1">Contact</h3><span class="text-gray-600">+60 3-1234
                                    5678</span>
                            </div>
                        </div>
                        <a href="https://maps.google.com/?q=Jalan+Rimbunan+Raya,+Kepong,+Kuala+Lumpur" target="_blank"
                            class="btn btn-emerald btn-block"
                            style="margin-top:1rem;border-radius:0.75rem;padding:1rem;">
                            <svg width="20" height="20" fill="none" stroke="currentColor" stroke-width="2"
                                viewBox="0 0 24 24" style="margin-right:0.5rem;">
                                <polygon points="3 11 22 2 13 21 11 13 3 11" />
                            </svg>
                            Get Directions
                        </a>
                    </div>
                </div>
            </div>
        </main>

        <%@ include file="Layout/footer.jsp" %>