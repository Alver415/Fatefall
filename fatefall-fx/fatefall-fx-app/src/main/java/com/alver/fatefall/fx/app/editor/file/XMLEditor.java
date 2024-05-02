package com.alver.fatefall.fx.app.editor.file;

import org.fxmisc.flowless.VirtualizedScrollPane;
import org.fxmisc.richtext.CodeArea;
import org.fxmisc.richtext.LineNumberFactory;
import org.fxmisc.richtext.model.StyleSpans;
import org.fxmisc.richtext.model.StyleSpansBuilder;

import java.util.Collection;
import java.util.Collections;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.alver.jfxtra.util.JFXUtils.run;
import static com.alver.jfxtra.util.JFXUtils.runFX;

public class XMLEditor extends VirtualizedScrollPane<CodeArea> {

	private static final Pattern XML_TAG = Pattern.compile(
			"(?<ELEMENT>(</?\\h*)(\\w+)([^<>]*)(\\h*/?>))|" +
					"(?<IMPORT>(<\\?/?\\h*)(\\w+)([^<>]*)(\\h*\\?>))|" +
					"(?<COMMENT><!--(.|\\v)+?-->)");

	private static final Pattern ATTRIBUTES = Pattern.compile("(\\w+\\h*)(=)(\\h*\"[^\"]+\")");

	private static final int GROUP_OPEN_BRACKET = 2;
	private static final int GROUP_ELEMENT_NAME = 3;
	private static final int GROUP_ATTRIBUTES_SECTION = 4;
	private static final int GROUP_CLOSE_BRACKET = 5;
	private static final int GROUP_ATTRIBUTE_NAME = 1;
	private static final int GROUP_EQUAL_SYMBOL = 2;
	private static final int GROUP_ATTRIBUTE_VALUE = 3;

	private static final String sampleCode = String.join("\n", new String[]{
			"<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>",
			"<!-- Sample XML -->",
			"< orders >",
			"	<Order number=\"1\" table=\"center\">",
			"		<items>",
			"			<Item>",
			"				<type>ESPRESSO</type>",
			"				<shots>2</shots>",
			"				<iced>false</iced>",
			"				<orderNumber>1</orderNumber>",
			"			</Item>",
			"			<Item>",
			"				<type>CAPPUCCINO</type>",
			"				<shots>1</shots>",
			"				<iced>false</iced>",
			"				<orderNumber>1</orderNumber>",
			"			</Item>",
			"			<Item>",
			"			<type>LATTE</type>",
			"				<shots>2</shots>",
			"				<iced>false</iced>",
			"				<orderNumber>1</orderNumber>",
			"			</Item>",
			"			<Item>",
			"				<type>MOCHA</type>",
			"				<shots>3</shots>",
			"				<iced>true</iced>",
			"				<orderNumber>1</orderNumber>",
			"			</Item>",
			"		</items>",
			"	</Order>",
			"</orders>"
	});

	public XMLEditor() {
		super(new CodeArea());
		CodeArea codeArea = getContent();
		codeArea.setParagraphGraphicFactory(LineNumberFactory.get(codeArea));

		codeArea.textProperty().addListener((obs, oldText, newText) -> {
			run(() -> {
				StyleSpans<Collection<String>> styleSpans = computeHighlighting(newText);
				runFX(() -> codeArea.setStyleSpans(0, styleSpans));

			});
		});
		codeArea.replaceText(0, 0, sampleCode);

		codeArea.getStylesheets().add(XMLEditor.class.getResource("xml-highlighting.css").toExternalForm());
	}

	private static StyleSpans<Collection<String>> computeHighlighting(String text) {

		Matcher matcher = XML_TAG.matcher(text);
		int lastKwEnd = 0;
		StyleSpansBuilder<Collection<String>> spansBuilder = new StyleSpansBuilder<>();
		while (matcher.find()) {

			spansBuilder.add(Collections.emptyList(), matcher.start() - lastKwEnd);
			if (matcher.group("IMPORT") != null) {
				spansBuilder.add(Collections.singleton("import"), matcher.end() - matcher.start());
			} else if (matcher.group("COMMENT") != null) {
				spansBuilder.add(Collections.singleton("comment"), matcher.end() - matcher.start());
			} else {
				if (matcher.group("ELEMENT") != null) {
					String attributesText = matcher.group(GROUP_ATTRIBUTES_SECTION);

					spansBuilder.add(
							Collections.singleton("tagmark"),
							matcher.end(GROUP_OPEN_BRACKET) - matcher.start(GROUP_OPEN_BRACKET));
					spansBuilder.add(
							Collections.singleton("anytag"),
							matcher.end(GROUP_ELEMENT_NAME) - matcher.end(GROUP_OPEN_BRACKET));

					if (!attributesText.isEmpty()) {

						lastKwEnd = 0;

						Matcher amatcher = ATTRIBUTES.matcher(attributesText);
						while (amatcher.find()) {
							spansBuilder.add(Collections.emptyList(), amatcher.start() - lastKwEnd);
							spansBuilder.add(
									Collections.singleton("attribute"),
									amatcher.end(GROUP_ATTRIBUTE_NAME) - amatcher.start(GROUP_ATTRIBUTE_NAME));
							spansBuilder.add(
									Collections.singleton("tagmark"),
									amatcher.end(GROUP_EQUAL_SYMBOL) - amatcher.end(GROUP_ATTRIBUTE_NAME));
							spansBuilder.add(
									Collections.singleton("avalue"),
									amatcher.end(GROUP_ATTRIBUTE_VALUE) - amatcher.end(GROUP_EQUAL_SYMBOL));
							lastKwEnd = amatcher.end();
						}
						if (attributesText.length() > lastKwEnd)
							spansBuilder.add(Collections.emptyList(), attributesText.length() - lastKwEnd);
					}

					lastKwEnd = matcher.end(GROUP_ATTRIBUTES_SECTION);

					spansBuilder.add(Collections.singleton("tagmark"), matcher.end(GROUP_CLOSE_BRACKET) - lastKwEnd);
				}
			}
			lastKwEnd = matcher.end();
		}
		spansBuilder.add(Collections.emptyList(), text.length() - lastKwEnd);
		return spansBuilder.create();
	}
}