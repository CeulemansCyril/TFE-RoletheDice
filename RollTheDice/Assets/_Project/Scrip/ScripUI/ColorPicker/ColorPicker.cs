using System;

using TMPro;

using UnityEngine;
using UnityEngine.UI;
using Color = UnityEngine.Color;
using ColorUtility = UnityEngine.ColorUtility;

namespace Assets._Project.Scrip.ScripUI.ColorPicker
{
    class ColorPicker : MonoBehaviour
    {
        [SerializeField] private Slider sliderRed;
        [SerializeField] private Slider sliderBlue;
        [SerializeField] private Slider sliderGreen;

        [SerializeField] private Button image;
        [SerializeField] private TMP_Text hexlabel;

        public Action<string> onColorChange;
        private Color currentColor = Color.white;

        private void Start()
        {
            sliderBlue.onValueChanged.AddListener(_ => UpdateColor());
            sliderGreen.onValueChanged.AddListener(_ => UpdateColor());
            sliderRed.onValueChanged.AddListener(_ => UpdateColor());

            image.onClick.AddListener(OncolorClickChange);

            UpdateColor();
        }

        private void UpdateColor()
        {
            Color color = new Color(sliderRed.value, sliderGreen.value, sliderBlue.value); 
            currentColor = color;

            Image img = image.GetComponent<Image>();
            img.color = color;

            string hex = ColorUtility.ToHtmlStringRGB(color);
            hexlabel.text = "#" + hex;

            if(hexlabel != null) hexlabel.text = hex;

            
            
        }

        public Color GetCurrentColor() {  return currentColor; }

        private void OncolorClickChange()
        {
            string hex = ColorUtility.ToHtmlStringRGB(currentColor);
            hex = "#" + hex; 
            onColorChange?.Invoke(hex);
        }
    }
}
