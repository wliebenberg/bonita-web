/**
 * Copyright (C) 2011 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2.0 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package org.bonitasoft.console.client.admin.organization.role;

import static org.bonitasoft.web.toolkit.client.common.i18n.AbstractI18n._;

import java.util.ArrayList;
import java.util.List;

import org.bonitasoft.web.rest.model.identity.RoleDefinition;
import org.bonitasoft.web.rest.model.identity.RoleItem;
import org.bonitasoft.web.toolkit.client.ui.JsId;
import org.bonitasoft.web.toolkit.client.ui.Page;
import org.bonitasoft.web.toolkit.client.ui.action.form.AddItemFormAction;
import org.bonitasoft.web.toolkit.client.ui.component.form.Form;

/**
 * @author Colin PUY
 * 
 */
public class AddRolePage extends Page {

    public static final String TOKEN = "addrole";
    
    public static final List<String> PRIVILEGES = new ArrayList<String>();
    
    static {
        PRIVILEGES.add(RoleListingPage.TOKEN);
    }

    @Override
    public void defineTitle() {
        setTitle(_("Create a role"));
    }

    @Override
    public void buildView() {
        addBody(addRoleForm());
    }

    private Form addRoleForm() {
        return new EditRoleForm()
                .addButton(new JsId("create"), _("Create"), _("Create this role"), new AddItemFormAction<RoleItem>(RoleDefinition.get()))
                .addCancelButton();
    }

    @Override
    public String defineToken() {
        return TOKEN;
    }

}
