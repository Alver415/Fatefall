package com.alver.fatefall.templatebuilder.components.builders;

import com.alver.fatefall.templatebuilder.components.block.TextBlock;

public class TextBlockBuilder extends AbstractBuilder<TextBlock> {

    protected TextBlockBuilder() {
        super(new TextBlock());
    }

    public static TextBlockBuilder builder(){
        return new TextBlockBuilder();
    }

    public TextBlockBuilder text(String text) {
        object.setText(text);
        return this;
    }
}