/**   
 * License Agreement for Jaeksoft OpenSearchServer
 *
 * Copyright (C) 2010 Emmanuel Keller / Jaeksoft
 * 
 * http://www.open-search-server.com
 * 
 * This file is part of Jaeksoft OpenSearchServer.
 *
 * Jaeksoft OpenSearchServer is free software: you can redistribute it and/or
 * modify it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 * Jaeksoft OpenSearchServer is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with Jaeksoft OpenSearchServer. 
 *  If not, see <http://www.gnu.org/licenses/>.
 **/

package com.jaeksoft.searchlib.web.controller.schema;

import com.jaeksoft.searchlib.SearchLibException;
import com.jaeksoft.searchlib.analysis.synonym.SynonymsManager;

public class SynonymsController extends CommonDirectoryController {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8145875561037662110L;

	public SynonymsController() throws SearchLibException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public SynonymsManager getManager() throws SearchLibException {
		return getClient().getSynonymsManager();
	}

}