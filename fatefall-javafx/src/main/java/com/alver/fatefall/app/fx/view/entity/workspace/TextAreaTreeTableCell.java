/*
 * Copyright (c) 2012, 2021, Oracle and/or its affiliates. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License version 2 only, as
 * published by the Free Software Foundation.  Oracle designates this
 * particular file as subject to the "Classpath" exception as provided
 * by Oracle in the LICENSE file that accompanied this code.
 *
 * This code is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License
 * version 2 for more details (a copy is included in the LICENSE file that
 * accompanied this code).
 *
 * You should have received a copy of the GNU General Public License version
 * 2 along with this work; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * Please contact Oracle, 500 Oracle Parkway, Redwood Shores, CA 94065 USA
 * or visit www.oracle.com if you need additional information or have any
 * questions.
 */

package com.alver.fatefall.app.fx.view.entity.workspace;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.util.Callback;
import javafx.util.StringConverter;
import javafx.util.converter.DefaultStringConverter;

/**
 * A class containing a {@link javafx.scene.control.TableCell} implementation that draws a
 * {@link TextArea} node inside the cell.
 *
 * <p>By default, the TextAreaTableCell is rendered as a {@link javafx.scene.control.Label} when not
 * being edited, and as a TextArea when in editing mode. The TextArea will, by
 * default, stretch to fill the entire table cell.
 *
 * @param <S> The type of the TreeTableView generic type
 * @param <T> The type of the elements contained within the TreeTableColumn.
 * @since JavaFX 8.0
 */
public class TextAreaTreeTableCell<S, T> extends TreeTableCell<S, T> {

    /* *************************************************************************
     *                                                                         *
     * Static cell factories                                                   *
     *                                                                         *
     **************************************************************************/

    /**
     * Provides a {@link TextArea} that allows editing of the cell content when
     * the cell is double-clicked, or when
     * {@link javafx.scene.control.TreeTableView#edit(int, javafx.scene.control.TreeTableColumn)} is called.
     * This method will only  work on {@link TreeTableColumn} instances which are of
     * type String.
     *
     * @param <S> The type of the TreeTableView generic type
     * @return A {@link Callback} that can be inserted into the
     * {@link TreeTableColumn#cellFactoryProperty() cell factory property} of a
     * TreeTableColumn, that enables textual editing of the content.
     */
    public static <S> Callback<TreeTableColumn<S, String>, TreeTableCell<S, String>> forTreeTableColumn() {
        return forTreeTableColumn(new DefaultStringConverter());
    }

    /**
     * Provides a {@link TextArea} that allows editing of the cell content when
     * the cell is double-clicked, or when
     * {@link javafx.scene.control.TreeTableView#edit(int, javafx.scene.control.TreeTableColumn) } is called.
     * This method will work  on any {@link TreeTableColumn} instance, regardless of
     * its generic type. However, to enable this, a {@link StringConverter} must
     * be provided that will convert the given String (from what the user typed
     * in) into an instance of type T. This item will then be passed along to the
     * {@link TreeTableColumn#onEditCommitProperty()} callback.
     *
     * @param <S>       The type of the TreeTableView generic type
     * @param <T>       The type of the elements contained within the TreeTableColumn
     * @param converter A {@link StringConverter} that can convert the given String
     *                  (from what the user typed in) into an instance of type T.
     * @return A {@link Callback} that can be inserted into the
     * {@link TreeTableColumn#cellFactoryProperty() cell factory property} of a
     * TreeTableColumn, that enables textual editing of the content.
     */
    public static <S, T> Callback<TreeTableColumn<S, T>, TreeTableCell<S, T>> forTreeTableColumn(
            final StringConverter<T> converter) {
        return list -> new TextAreaTreeTableCell<S, T>(converter);
    }


    /* *************************************************************************
     *                                                                         *
     * Fields                                                                  *
     *                                                                         *
     **************************************************************************/

    private TextArea textArea;



    /* *************************************************************************
     *                                                                         *
     * Constructors                                                            *
     *                                                                         *
     **************************************************************************/

    /**
     * Creates a default TextAreaTreeTableCell with a null converter. Without a
     * {@link StringConverter} specified, this cell will not be able to accept
     * input from the TextArea (as it will not know how to convert this back
     * to the domain object). It is therefore strongly encouraged to not use
     * this constructor unless you intend to set the converter separately.
     */
    public TextAreaTreeTableCell() {
        this(null);
    }

    /**
     * Creates a TextAreaTreeTableCell that provides a {@link TextArea} when put
     * into editing mode that allows editing of the cell content. This method
     * will work on any TreeTableColumn instance, regardless of its generic type.
     * However, to enable this, a {@link StringConverter} must be provided that
     * will convert the given String (from what the user typed in) into an
     * instance of type T. This item will then be passed along to the
     * {@link TreeTableColumn#onEditCommitProperty()} callback.
     *
     * @param converter A {@link StringConverter converter} that can convert
     *                  the given String (from what the user typed in) into an instance of
     *                  type T.
     */
    public TextAreaTreeTableCell(StringConverter<T> converter) {
        this.getStyleClass().add("text-field-tree-table-cell");
        setConverter(converter);
    }



    /* *************************************************************************
     *                                                                         *
     * Properties                                                              *
     *                                                                         *
     **************************************************************************/

    // --- converter
    private ObjectProperty<StringConverter<T>> converter =
            new SimpleObjectProperty<StringConverter<T>>(this, "converter");

    /**
     * The {@link StringConverter} property.
     *
     * @return the {@link StringConverter} property
     */
    public final ObjectProperty<StringConverter<T>> converterProperty() {
        return converter;
    }

    /**
     * Sets the {@link StringConverter} to be used in this cell.
     *
     * @param value the {@link StringConverter} to be used in this cell
     */
    public final void setConverter(StringConverter<T> value) {
        converterProperty().set(value);
    }

    /**
     * Returns the {@link StringConverter} used in this cell.
     *
     * @return the {@link StringConverter} used in this cell
     */
    public final StringConverter<T> getConverter() {
        return converterProperty().get();
    }



    /* *************************************************************************
     *                                                                         *
     * Public API                                                              *
     *                                                                         *
     **************************************************************************/

    /**
     * {@inheritDoc}
     */
    @Override
    public void startEdit() {
        super.startEdit();
        if (!isEditing()) {
            return;
        }

        if (textArea == null) {
            textArea = createTextArea(this, getConverter());
        }

        startEdit(this, getConverter(), null, null, textArea);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void cancelEdit() {
        super.cancelEdit();
        cancelEdit(this, getConverter(), null);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateItem(T item, boolean empty) {
        super.updateItem(item, empty);
        updateItem(this, getConverter(), null, null, textArea);
    }


    static <T> TextArea createTextArea(final Cell<T> cell, final StringConverter<T> converter) {
        final TextArea textArea = new TextArea(getItemText(cell, converter));

        // Use onAction here rather than onKeyReleased (with check for Enter),
        // as otherwise we encounter RT-34685
        textArea.setOnKeyPressed(event -> {
            if (event.isControlDown() && event.getCode().equals(KeyCode.ENTER)) {
                if (converter == null) {
                    throw new IllegalStateException(
                            "Attempting to convert text input into Object, but provided "
                                    + "StringConverter is null. Be sure to set a StringConverter "
                                    + "in your cell factory.");
                }
                cell.commitEdit(converter.fromString(textArea.getText()));
                event.consume();
            }
        });
        textArea.setOnKeyReleased(t -> {
            if (t.getCode() == KeyCode.ESCAPE) {
                cell.cancelEdit();
                t.consume();
            }
        });
        return textArea;
    }


    private static <T> String getItemText(Cell<T> cell, StringConverter<T> converter) {
        return converter == null ?
                cell.getItem() == null ? "" : cell.getItem().toString() :
                converter.toString(cell.getItem());
    }

    static <T> void updateItem(final Cell<T> cell,
                               final StringConverter<T> converter,
                               final TextArea textArea) {
        updateItem(cell, converter, null, null, textArea);
    }

    static <T> void updateItem(final Cell<T> cell,
                               final StringConverter<T> converter,
                               final HBox hbox,
                               final Node graphic,
                               final TextArea textArea) {
        if (cell.isEmpty()) {
            cell.setText(null);
            cell.setGraphic(null);
        } else {
            if (cell.isEditing()) {
                if (textArea != null) {
                    textArea.setText(getItemText(cell, converter));
                }
                cell.setText(null);

                if (graphic != null) {
                    hbox.getChildren().setAll(graphic, textArea);
                    cell.setGraphic(hbox);
                } else {
                    cell.setGraphic(textArea);
                }
            } else {
                cell.setText(getItemText(cell, converter));
                cell.setGraphic(graphic);
            }
        }
    }

    static <T> void startEdit(final Cell<T> cell,
                              final StringConverter<T> converter,
                              final HBox hbox,
                              final Node graphic,
                              final TextArea textArea) {
        if (textArea != null) {
            textArea.setText(getItemText(cell, converter));
        }
        cell.setText(null);

        if (graphic != null) {
            hbox.getChildren().setAll(graphic, textArea);
            cell.setGraphic(hbox);
        } else {
            cell.setGraphic(textArea);
        }

        textArea.selectAll();

        // requesting focus so that key input can immediately go into the
        // TextField (see RT-28132)
        textArea.requestFocus();
    }

    static <T> void cancelEdit(Cell<T> cell, final StringConverter<T> converter, Node graphic) {
        cell.setText(getItemText(cell, converter));
        cell.setGraphic(graphic);
    }

}
