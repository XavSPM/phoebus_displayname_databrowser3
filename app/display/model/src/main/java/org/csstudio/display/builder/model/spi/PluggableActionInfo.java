/*
 * Copyright (C) 2023 European Spallation Source ERIC.
 *
 *  This program is free software; you can redistribute it and/or
 *  modify it under the terms of the GNU General Public License
 *  as published by the Free Software Foundation; either version 2
 *  of the License, or (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program; if not, write to the Free Software
 *  Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 *
 */

package org.csstudio.display.builder.model.spi;

import javafx.scene.Node;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import org.csstudio.display.builder.model.Widget;
import org.csstudio.display.builder.model.persist.ModelReader;
import org.csstudio.display.builder.model.persist.ModelWriter;
import org.w3c.dom.Element;

import javax.xml.stream.XMLStreamWriter;
import java.util.List;

public interface PluggableActionInfo {

    /**
     * @param actionId Legacy action id, e.g. open_display
     * @return <code>true</code> if the input string is implemented by the {@link PluggableActionInfo}.
     */
    default boolean matchesLegacyAction(String actionId) {
        return false;
    }

    /**
     * The type of action, which is either a fully qualified class name, or a legacy identifier
     * string like 'open_display'.
     *
     * @return The action 'type'.
     */
    String getType();

    /**
     * Image shown in drop-down in editor and runtime.
     *
     * @return An {@link Image} representing the action.
     */
    Image getImage();

    /**
     * @return Default or user-defined description string.
     */
    String getDescription();

    /**
     * @param description User-defined string, potentially overriding default.
     */
    void setDescription(String description);

    /**
     * @return The editor UI for the action
     */
    Node getEditor(Widget widget);

    /**
     * Reads implementation specific XML.
     *
     * @param modelReader A {@link ModelReader}
     * @param actionXml   The {@link Element} holding the &lt;action&gt; tag data.
     * @throws Exception On failure
     */
    void readFromXML(final ModelReader modelReader, final Element actionXml) throws Exception;


    /**
     * Writes implementation specific XML starting from the &lt;action&gt; node.
     *
     * @param modelWriter A {@link ModelWriter}
     * @param writer A {@link XMLStreamWriter}
     * @throws Exception On failure
     */
    void writeToXML(final ModelWriter modelWriter, final XMLStreamWriter writer) throws Exception;

    /**
     * Used to define action behavior if it depends on key modifiers, e.g. open display in specific target.
     *
     * @param event The {@link MouseEvent} holding information on potential modifier keys.
     */
    default void setModifiers(MouseEvent event) {
    }

    void execute(Widget sourceWidget, Object... arguments);

    /**
     * @return A {@link List} of {@link MenuItem}s for the widget's context menu.
     * Defaults to <code>null</code>.
     */
    default List<MenuItem> getContextMenuItems(Widget widget) {
        return null;
    }
}
