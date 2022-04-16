function transform(input){
    console.log("input:");
    console.log(input);
    var card = JSON.parse(input);

    var name = card.name;
    var index = name.indexOf(",");
    var base = index > 0 ? name.substring(0, index) : name;
    var title = titles[Math.floor(Math.random() * titles.length)];
    card.name = base + ", " + title;

    var output = card;

    console.log("output:");
    console.log(JSON.stringify(output));

    return JSON.stringify(output);
}

titles = [
    "the Bumbling Bozo",
    "the Wimpy Wuss",
    "the Fumbling Fatso",
    "the Wallowing Wanker",
    "the Whimpering Weirdo",
    "Pompus Porker",
    "Old Geezer",
    "Psycho Spastic",
    "Sentient Doorknob",
    "the Inbreeded Inbreeder",
    "the Literal Birdbrain",
    "Lamebrain",
    "Boogerface",
    "Poo-poo head",
    "Village idiot",
];

transform(input);