package cc.happyareabean.countryflags;

import org.apache.commons.lang3.LocaleUtils;

import java.util.*;

public class CountryFlags {

	public static void main(String[] args)  {

		Locale.setDefault(Locale.ENGLISH); // Set the JVM locale to english to show english country and language name

		String[] tableHeaders = { "Language Name", "Language Code",  "Country Code (If available)", "Country Name", "Country Flags"};
		List<List<String>> tableData = new ArrayList<>();

		// Markdown table text align
		tableData.add(new ArrayList<>() {{
			add(":---");
			add(":---:");
			add(":---:");
			add(":---:");
			add(":---:");
		}});

		List<String> languageCodes = Arrays.asList(
				"bg",
				"cs",
				"da",
				"de",
				"el",
				"en",
				"es",
				"et",
				"fi",
				"fr",
				"hu",
				"it",
				"ja",
				"lt",
				"lv",
				"nl",
				"pl",
				"pt",
				"ro",
				"ru",
				"sk",
				"sl",
				"sv",
				"zh"
		);

		// Use all the available locale
		LocaleUtils.availableLocaleList().forEach((c) -> {
			if (!c.toString().isEmpty()) {
				String[] splitCountry = c.toString().split("_");
				tableData.add(new ArrayList<>() {{
					add(c.getDisplayLanguage());
					add(c.toString());
					add(splitCountry.length > 1 ? splitCountry[1] : splitCountry[0]);
					add(c.getDisplayCountry());
					add(Country.getCountryFlagByCountryCode(splitCountry.length > 1 ? splitCountry[1] : splitCountry[0]));
				}});
			}
		});

		// Use only language codes
//		languageCodes.forEach((s -> {
//			LocaleUtils.countriesByLanguage(s).forEach((c) -> {
//				String[] splitCountry = c.toString().split("_");
//				data.add(new ArrayList<>() {{
//					add(c.getDisplayLanguage());
//					add(c.toString());
//					add(splitCountry[1]);
//					add(c.getDisplayCountry());
//					add(Country.getCountryFlagByCountryCode(splitCountry[1]));
//				}});
//			});
//		}));

		// original List<List<String>>
		// printing the original List<List<String>>
		String[][] nestedArray = tableData
				// using the stream API
				.stream()
				// mapping each `List`...
				.sorted(Comparator.comparing(stringList -> stringList.get(0)))
				.map(
						// ... to a resulting array
						(l) -> l.toArray(new String[0])
				).toList()
				// converting the resulting List<String[]> to a String[][]
				.toArray(new String[tableData.size()][]);

		System.out.println(MarkdownTable.of(tableHeaders, nestedArray));

		// Use this if you want to print normal table instead
		// System.out.println(FlipTable.of(tableHeaders, nestedArray));
	}

}
