
var width = 300;
var height = 416;
var data = [];
for (var y = 0 ; y < height ; y++) {
    for (var x = 0 ; x < width; x++) {
        var index = x + y * width;
        var ratio = index / (width * height);
        var max = 2147483647;
        var value = ((ratio * 2 * max) % max);

        data.push(value);
    }
}
data;