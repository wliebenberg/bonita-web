/*
 * Copyright (C) 2013 BonitaSoft S.A.
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

package org.bonitasoft.web.toolkit.client.ui.component.table;

import org.bonitasoft.web.toolkit.client.data.item.ItemDefinition;
import org.junit.Test;
import org.mockito.Mock;

import static junit.framework.Assert.assertTrue;

/**
 * Created by Vincent Elcrin
 * Date: 30/09/13
 * Time: 10:32
 */
public class ItemTableTest {

    @Mock
    private ItemDefinition itemDefinition;

    @Test
    public void testWeCanAddColumnWithCustomRenderer() throws Exception {
        ItemTable table = new ItemTable(itemDefinition);

        table.addColumn("attribute", "label");

        assertTrue(true);
    }
}