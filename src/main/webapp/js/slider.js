var lowerSlider = document.querySelector('#lower'),
    upperSlider = document.querySelector('#upper'),
    lowerVal = parseInt(lowerSlider.value),
    upperVal = parseInt(upperSlider.value);
updateSliderValue();

upperSlider.oninput = function () {
    lowerVal = parseInt(lowerSlider.value);
    upperVal = parseInt(upperSlider.value);

    if (upperVal < lowerVal + 4) {
        lowerSlider.value = upperVal - 4;

        if (lowerVal == lowerSlider.min) {
            upperSlider.value = 4;
        }
    }
    updateSliderValue();
};


lowerSlider.oninput = function () {
    lowerVal = parseInt(lowerSlider.value);
    upperVal = parseInt(upperSlider.value);

    if (lowerVal > upperVal - 4) {
        upperSlider.value = lowerVal + 4;

        if (upperVal === upperSlider.max) {
            lowerSlider.value = parseInt(upperSlider.max) - 4;
        }

    }
    updateSliderValue();
};


function updateSliderValue() {
    document.getElementById('upper-value').innerHTML = upperVal;
    document.getElementById('lower-value').innerHTML = lowerVal;

};