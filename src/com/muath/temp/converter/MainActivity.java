package com.muath.temp.converter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivity extends Activity {

	private EditText tempEditText;
	private TextView resultTextView;
	private TextView conversionFormulaDisplayTextView;
	private RadioGroup fromRadioGroup;
	private RadioGroup toRadioGroup;
	
	private RadioButton fromFahrenhaitRadioButton;
	private RadioButton fromCelsiusRadioButton;
	private RadioButton fromKelvinRadioButton;
	private RadioButton toFahrenheitRadioButton;
	private RadioButton toCelsiusRadioButton;
	private RadioButton toKelvinRadioButton;
	
	private ImageView errorImage;
	
	private Button convertButton;
	
	private double result = 0;
	
	private final String FAHRENHEIT_TO_CELSIUS_FORMULA =
			"Celsius = (5/9) * (Faherenheit - 32)";
	private final String FAHRENHEIT_TO_KELVIN_FORMULA =
			"Kelvin = (Fahrenheit - 32) * (5/9) + 273.15";
	private final String CELSIUS_TO_FAHRENHEIT_FORMULA = 
			"Fahrenheit = (9/5) * Celsius + 32";
	private final String CELSIUS_TO_KELVIN_FORMULA = 
			"Kelvin = Celsius + 273.15";
	private final String KELVIN_TO_FAHRENHEIT_FORMULA = 
			"Fahrenheit = (9/5) * (Kelvin - 273.15) + 32";
	private final String KELVIN_TO_CELSIUS_FORMULA = 
			"Celsius = Kelvin - 273.15";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		tempEditText = (EditText) findViewById(R.id.tempEditText);
		resultTextView = (TextView) findViewById(R.id.resultTextView);
		conversionFormulaDisplayTextView = 
				(TextView) findViewById(R.id.conversionFormulaDisplayTextView);
		fromRadioGroup = (RadioGroup) findViewById(R.id.fromRadioGroup);
		toRadioGroup = (RadioGroup) findViewById(R.id.toRadioGroup);
		fromFahrenhaitRadioButton = (RadioButton) findViewById(R.id.fromFahrenheitRadio);
		fromCelsiusRadioButton = (RadioButton) findViewById(R.id.fromCelsiusRadio);
		fromKelvinRadioButton = (RadioButton) findViewById(R.id.fromKelvinRadio);
		toFahrenheitRadioButton = (RadioButton) findViewById(R.id.toFahrenheitRadio);
		toKelvinRadioButton = (RadioButton) findViewById(R.id.toKelvinRadio);
		toCelsiusRadioButton = (RadioButton) findViewById(R.id.toCelsiusRadio);
		convertButton = (Button) findViewById(R.id.convertButton);
		convertButton.setOnClickListener(convertButtonListener);
	}
	
	private double fahrenheitToCelsius (double temp_par) { 
		 
		return ((5.0/9.0) * (temp_par - 32.0));
	}
	
	private double fahrenheitToKelvin (double temp_par) { 
		return ((temp_par - 32.0) * (5.0/9.0) + 273.15);
	}
	
	private double celsiusToFahrenheit (double temp_par) { 
		return ((9.0/5.0) * temp_par + 32.0);
	}
	
	private double celsiusToKelvin (double temp_par) { 
		return (temp_par + 273.15);
	}
	
	private double kelvinToFahrenheit (double temp_par) { 
		return ((9.0/5.0) * (temp_par - 273.15) + 32.0);
	}
	
	private double kelvinToCelsius (double temp_par) { 
		return (temp_par - 273.15);
	}
	
	OnClickListener convertButtonListener = new OnClickListener() {
		public void onClick(View v) {
			((InputMethodManager) getSystemService(
					Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(
							tempEditText.getWindowToken(), 0);
			double temp = 0;
			try {
				temp = Double.parseDouble(
					tempEditText.getText().toString());
			}
			catch (NumberFormatException e) {
				AlertDialog.Builder builder = 
						new AlertDialog.Builder(MainActivity.this);
				builder.setMessage(R.string.wrongInputMessage);
				builder.setTitle(R.string.alertTitle);
				builder.setPositiveButton(R.string.reset, null);
				builder.setIcon(android.R.drawable.ic_delete);
				AlertDialog errorDialog = builder.create();
				errorDialog.show();
				reset();
				return;
			}
			int selectedFromRadioGroup = 
					fromRadioGroup.getCheckedRadioButtonId();
			int selectedToRadioGroup = 
					toRadioGroup.getCheckedRadioButtonId();
			
			//From fahrenheit to celsius
			if(selectedFromRadioGroup == fromFahrenhaitRadioButton.getId()
					&& selectedToRadioGroup == toCelsiusRadioButton.getId()) 
			{
				resultTextView.setText(
						String.format(
								"%.2f Fahrenheit = %.2f Celsius", temp,fahrenheitToCelsius(temp)));
				conversionFormulaDisplayTextView.setText(FAHRENHEIT_TO_CELSIUS_FORMULA);
			} 
			
			//From fahrenheit to kalvin
			else if (selectedFromRadioGroup == fromFahrenhaitRadioButton.getId()
					&& selectedToRadioGroup == toKelvinRadioButton.getId()) {
				resultTextView.setText(
						String.format(
								"%.2f Fahrenheit = %.2f Kelvin", temp, fahrenheitToKelvin(temp)));
				conversionFormulaDisplayTextView.setText(FAHRENHEIT_TO_KELVIN_FORMULA);
			}
			
			//From Celcius to fahernheit
			else if (selectedFromRadioGroup == fromCelsiusRadioButton.getId()
					&& selectedToRadioGroup == toFahrenheitRadioButton.getId()) {
				resultTextView.setText(
						String.format(
								"%.2f Celsius = %.2f Fahrenheit", temp,celsiusToFahrenheit(temp)));
				conversionFormulaDisplayTextView.setText(CELSIUS_TO_FAHRENHEIT_FORMULA);
			}
			
			//From Celsius to Kalvin
			else if (selectedFromRadioGroup == fromCelsiusRadioButton.getId()
					&& selectedToRadioGroup == toKelvinRadioButton.getId()) {
				resultTextView.setText(
						String.format(
								"%.2f Celsius = %.2f Kelvin", temp, celsiusToKelvin(temp)));
				conversionFormulaDisplayTextView.setText(CELSIUS_TO_KELVIN_FORMULA);
			}
			
			//From Kelvin to Fahrenheit
			else if (selectedFromRadioGroup == fromKelvinRadioButton.getId()
					&& selectedToRadioGroup == toFahrenheitRadioButton.getId()) {
				resultTextView.setText(
						String.format(
								"%.2f Kelvin = %.2f Fahrenheit", temp, kelvinToFahrenheit(temp)));
				conversionFormulaDisplayTextView.setText(KELVIN_TO_FAHRENHEIT_FORMULA);
			}
			
			//From Kelvin to Celsius
			else if (selectedFromRadioGroup == fromKelvinRadioButton.getId()
					&& selectedToRadioGroup == toCelsiusRadioButton.getId()) {
				resultTextView.setText(
						String.format(
								"%.2f Kelvin = %.2f Celsius", temp, kelvinToCelsius(temp)));
				conversionFormulaDisplayTextView.setText(KELVIN_TO_CELSIUS_FORMULA);
			}
			
			else
			{
				AlertDialog.Builder builder = 
						new AlertDialog.Builder(MainActivity.this);
				builder.setMessage(R.string.unitsErrorMessage);
				builder.setTitle(R.string.alertTitle);
				builder.setPositiveButton(R.string.reset, null);
				builder.setIcon(android.R.drawable.ic_delete);
				AlertDialog errorDialog = builder.create();
				errorDialog.show();
				reset();
				
			}
		}
	};
	
	//Clears formual textView and result textView and keeps the tempEditText.
	//Also, it changes the options to be from Fahreinheit to celsius.
	private void reset () {
		resultTextView.setText("");
		conversionFormulaDisplayTextView.setText("");
		fromRadioGroup.check(R.id.fromFahrenheitRadio);
		toRadioGroup.check(R.id.toCelsiusRadio);
	}
	
	
}
