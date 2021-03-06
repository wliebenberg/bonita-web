/**
 * Copyright (C) 2011 BonitaSoft S.A.
 * BonitaSoft, 31 rue Gustave Eiffel - 38000 Grenoble
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2.0 of the License, or
 * (at your option) any later version.
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package org.bonitasoft.web.rest.server.datastore.organization;

import org.bonitasoft.engine.identity.User;
import org.bonitasoft.engine.identity.UserCreator;
import org.bonitasoft.engine.identity.UserUpdater;
import org.bonitasoft.engine.search.SearchResult;
import org.bonitasoft.engine.session.APISession;
import org.bonitasoft.web.rest.model.identity.UserItem;
import org.bonitasoft.web.rest.server.datastore.CommonDatastore;
import org.bonitasoft.web.rest.server.datastore.filter.Filters;
import org.bonitasoft.web.rest.server.datastore.utils.SearchOptionsCreator;
import org.bonitasoft.web.rest.server.datastore.utils.Sorts;
import org.bonitasoft.web.rest.server.engineclient.EngineAPIAccessor;
import org.bonitasoft.web.rest.server.engineclient.EngineClientFactory;
import org.bonitasoft.web.rest.server.engineclient.UserEngineClient;
import org.bonitasoft.web.rest.server.framework.api.DatastoreHasGet;
import org.bonitasoft.web.rest.server.framework.search.ItemSearchResult;
import org.bonitasoft.web.toolkit.client.data.APIID;

import java.util.List;
import java.util.Map;

/**
 * @author Séverin Moussel
 */
public class UserDatastore extends CommonDatastore<UserItem, User>
        implements DatastoreHasGet<UserItem> {

    private EngineClientFactory engineClientFactory;
    private UserItemConverter userItemConverter;
    
    public UserDatastore(final APISession engineSession) {
        super(engineSession);
        userItemConverter = new UserItemConverter();
        engineClientFactory = new EngineClientFactory(new EngineAPIAccessor(engineSession));
    }

    // //////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // C.R.U.D.
    // //////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public UserItem add(final UserItem user) {
        UserCreator userCreator = new UserCreatorConverter().convert(user);
        User createdUser = getUserEngineClient().create(userCreator);
        return userItemConverter.convert(createdUser);
    }

    public UserItem update(final APIID id, final Map<String, String> attributes) {
        UserUpdater userUpdater = new UserUpdaterConverter().convert(attributes);
        User user = getUserEngineClient().update(id.toLong(), userUpdater);
        return userItemConverter.convert(user);
    }

    public UserItem get(final APIID id) {
        User user = getUserEngineClient().get(id.toLong());
        return userItemConverter.convert(user);
    }

    /**
     * Search for users
     * 
     * @param page
     *            The page to display
     * @param resultsByPage
     *            The number of results by page
     * @param search
     *            Search terms
     * @param filters
     *            The filters to doAuthorize. There will be an AND operand between filters.
     * @param orders
     *            The order to doAuthorize to the search
     * @return This method returns an ItemSearch result containing the returned data and information about the total possible results.
     */
    public ItemSearchResult<UserItem> search(final int page, final int resultsByPage, final String search,
            final Map<String, String> filters, final String orders) {
        
        SearchOptionsCreator searchOptionsCreator = new SearchOptionsCreator(page, resultsByPage, search, 
                new Sorts(orders, new UserSearchAttributeConverter()), 
                new Filters(filters, new UserFilterCreator()));

        SearchResult<User> engineSearchResults = getUserEngineClient().search(searchOptionsCreator.create());
        
        return new ItemSearchResult<UserItem>(page, resultsByPage, engineSearchResults.getCount(), 
                userItemConverter.convert(engineSearchResults.getResult()));
    }


    /**
     * Delete users
     * 
     * @param ids
     */
    public void delete(final List<APIID> ids) {
        getUserEngineClient().delete(APIID.toLongList(ids));
    }

    // //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // CONVERTS
    // //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    // protected for tests
    protected UserEngineClient getUserEngineClient() {
        return engineClientFactory.createUserEngineClient();
    }

    @Override
    protected UserItem convertEngineToConsoleItem(final User user) {
        throw new RuntimeException("Unimplemented method");
    }
}
