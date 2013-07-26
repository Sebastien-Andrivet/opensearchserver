/**   
 * License Agreement for OpenSearchServer
 *
 * Copyright (C) 2013 Emmanuel Keller / Jaeksoft
 * 
 * http://www.open-search-server.com
 * 
 * This file is part of OpenSearchServer.
 *
 * OpenSearchServer is free software: you can redistribute it and/or
 * modify it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 * OpenSearchServer is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with OpenSearchServer. 
 *  If not, see <http://www.gnu.org/licenses/>.
 **/
package com.jaeksoft.searchlib.webservice.analyzer;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.jaeksoft.searchlib.analysis.FilterScope;
import com.jaeksoft.searchlib.analysis.LanguageEnum;

@Path("/analyzer")
public interface RestAnalyzer {

	@GET
	@Produces(MediaType.APPLICATION_XML)
	@Path("/test/{index}/xml")
	public AnalyzerResult testXML(@PathParam("index") String use,
			@QueryParam("login") String login, @QueryParam("key") String key,
			@QueryParam("name") String name,
			@QueryParam("lang") LanguageEnum language,
			@QueryParam("scope") FilterScope scope,
			@QueryParam("text") String text);

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/test/{index}/json")
	public AnalyzerResult testJSON(@PathParam("index") String use,
			@QueryParam("login") String login, @QueryParam("key") String key,
			@QueryParam("name") String name,
			@QueryParam("lang") LanguageEnum language,
			@QueryParam("scope") FilterScope scope,
			@QueryParam("text") String text);

	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_XML)
	@Path("/test/{index}/xml")
	public AnalyzerResult testFormXML(@PathParam("index") String use,
			@FormParam("login") String login, @FormParam("key") String key,
			@FormParam("name") String name,
			@FormParam("lang") LanguageEnum language,
			@FormParam("scope") FilterScope scope,
			@FormParam("text") String text);

	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/test/{index}/json")
	public AnalyzerResult testFormJSON(@PathParam("index") String use,
			@FormParam("login") String login, @FormParam("key") String key,
			@FormParam("name") String name,
			@FormParam("lang") LanguageEnum language,
			@FormParam("scope") FilterScope scope,
			@FormParam("text") String text);
}
