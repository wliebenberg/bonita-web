/**
 * Copyright (C) 2012 BonitaSoft S.A.
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
package org.bonitasoft.web.rest.api.model.bpm.cases;

import org.bonitasoft.web.rest.api.model.bpm.AbstractDocumentItem;
import org.bonitasoft.web.toolkit.client.data.APIID;
import org.bonitasoft.web.toolkit.client.data.item.ItemDefinition;

/**
 * @author Paul AMAR
 * 
 */
public class CaseDocumentItem extends AbstractDocumentItem {

    public static final String ATTRIBUTE_CASE_ID = "caseId";

    /**
     * Default Constructor.
     */
    public CaseDocumentItem() {
        super();
    }

    @Override
    public ItemDefinition getItemDefinition() {
        return new CaseDocumentDefinition();
    }

    public APIID getProcessInstanceId() {
        return this.getAttributeValueAsAPIID(ATTRIBUTE_CASE_ID);
    }

    public void setProcessInstanceId(final long processInstanceId) {
        this.setAttribute(ATTRIBUTE_CASE_ID, processInstanceId);
    }

    public void setProcessInstanceId(final APIID processInstanceId) {
        this.setAttribute(ATTRIBUTE_CASE_ID, processInstanceId);
    }

    public void setProcessInstanceId(final String processInstanceId) {
        this.setAttribute(ATTRIBUTE_CASE_ID, processInstanceId);
    }

}