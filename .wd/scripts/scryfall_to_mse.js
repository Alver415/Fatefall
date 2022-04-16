function transform(input){
    console.log("input:");
    console.log(input);
    var card = JSON.parse(input);

    var now = new Date().toLocaleString();
    var split_index = card.type_line.indexOf(" — ");
    var super_type = split_index < 0 ? card.type_line : card.type_line.substring(0, split_index);
    var sub_type = split_index < 0 ? null : card.type_line.substring(split_index + 3);
    var rarity = getRarity(card);
    var casting_cost = card.mana_cost.replaceAll("{", "").replaceAll("}", "");

    var output = {
        has_styling: false,
//        time_created: now,
//        time_modified: now,
        name: card.name,
        casting_cost: casting_cost,
        image: card.image_uris.art_crop,
        super_type: super_type,
        sub_type: sub_type,
        rarity: rarity,
        rule_text: card.oracle_text,
        flavor_text: card.flavor_text,
        power: card.power,
        toughness: card.toughness
    };

    console.log("output:");
    console.log(JSON.stringify(output));

    return JSON.stringify(output);
}

function getRarity(card){
    switch(card.rarity){
        case "common": return "common";
        case "uncommon": return "uncommon";
        case "rare": return "rare";
        case "mythic": return "mythic rare";
        default: throw card.rarity + " is not a supported rarity.";
    }
}

transform(input);