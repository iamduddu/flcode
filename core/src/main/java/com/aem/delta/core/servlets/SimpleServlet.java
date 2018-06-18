/*
 *  Copyright 2015 Adobe Systems Incorporated
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package com.aem.delta.core.servlets;

import java.io.IOException;

import javax.jcr.Node;
import javax.jcr.Session;
import javax.servlet.Servlet;
import javax.servlet.ServletException;

import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.ConfigurationPolicy;
import org.apache.felix.scr.annotations.Properties;
import org.apache.felix.scr.annotations.Property;
import org.apache.felix.scr.annotations.Reference;
import org.apache.felix.scr.annotations.Service;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;

import com.aem.delta.lopa.model.SeatMapConfigMapper;
import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.PageManager;
import com.day.cq.wcm.api.WCMException;

/**
 * Servlet that writes some sample content into the response. It is mounted for
 * all resources of a specific Sling resource type. The
 * {@link SlingSafeMethodsServlet} shall be used for HTTP methods that are
 * idempotent. For write operations use the {@link SlingAllMethodsServlet}.
 */
@SuppressWarnings("serial")
@Component(policy = ConfigurationPolicy.REQUIRE, immediate = true, metatype = true, label = "CreateSimpleServlet")
@Service(Servlet.class)
@Properties({ @Property(name = "sling.servlet.paths", value = "/bin/delta/flightpage"),
		@Property(name = "sling.servlet.extensions", value = "json"),
		@Property(name = "sling.servlet.methods", value = { "GET" }) })
public class SimpleServlet extends SlingSafeMethodsServlet {

	private static final String PAGE_PATH = "/content/delta";
	private static final String PAGE_NAME = "airline1";
	private static final String TEMPLATE = "/apps/delta/templates/flight-page";
	private static final String PAGE_TITLE = "Delta flight page";

	@Reference
	private transient ResourceResolverFactory resolverFactory;
	
	@Reference
	private transient SeatMapConfigMapper seatMapConfigMapper;
	
	@Override
	protected void doGet(final SlingHttpServletRequest request, final SlingHttpServletResponse resp)
			throws ServletException, IOException {

		try {
			System.out.println("Aircraft-------->"+seatMapConfigMapper.getSeatMapConfigObject().getAircraftCodes());
			
			Page prodPage = null;
			ResourceResolver resolver = request.getResourceResolver();
			PageManager pageManager = resolver.adaptTo(PageManager.class);
			if (null != PAGE_PATH) {
				prodPage = pageManager.create(PAGE_PATH, PAGE_NAME, TEMPLATE, PAGE_TITLE);
			}
			Session session = resolver.adaptTo(Session.class);
			if (session != null) {
				Node pageNode = prodPage.adaptTo(Node.class);
				Node jcrNode = null;
				if (prodPage.hasContent()) {
					jcrNode = prodPage.getContentResource().adaptTo(Node.class);
				} else {
					jcrNode = pageNode.addNode("jcr:content", "cq:PageContent");
				}
				Node parNode = jcrNode.addNode("par");
				parNode.setProperty("sling:resourceType", "foundation/components/parsys");

				Node textNode = parNode.addNode("flight");				
				textNode.setProperty("cassId", "");
				textNode.setProperty("code", "");
				textNode.setProperty("ISeats", "");
				textNode.setProperty("lowerDeckImage", "");
				textNode.setProperty("sling:resourceType", "delta/components/content/flight");
				textNode.setProperty("uSeats", "");
				textNode.setProperty("upperDeckImage", "");
				session.save();
				session.refresh(true);
			}
		} catch (WCMException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
