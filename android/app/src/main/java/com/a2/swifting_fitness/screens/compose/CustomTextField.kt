package com.a2.swifting_fitness.screens.compose

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation


@Composable
fun CustomTextField(
    modifier: Modifier=Modifier,
    value: String,
    label: String,
    onValueChange: (String) -> Unit,
    enabled: Boolean = true,
    multiLine: Boolean = false,
    keyboardType: KeyboardType = KeyboardType.Text,
    action: ImeAction = ImeAction.Next,
    isPassword: Boolean = false,
    trailingIcon: @Composable (() -> Unit)?=null,
    leadingIcon: @Composable (() -> Unit)
) {

    val (passwordVisible, setPasswordVisible) = remember { mutableStateOf(!isPassword) }
    TextField(
        modifier = modifier
            .fillMaxWidth(),
        label = { Text(label) },
        value = value,
        leadingIcon = leadingIcon,
        trailingIcon = {
            trailingIcon?.invoke()
            if (isPassword) {
                val image = if (passwordVisible) Icons.Filled.Lock else Icons.Filled.CheckCircle
                val description = if (passwordVisible) "Hide password" else "Show password"
                IconButton(onClick = { setPasswordVisible(!passwordVisible) }) {
                    Icon(image, contentDescription = description)
                }

            }
        },
        onValueChange = { textFieldValue -> onValueChange(textFieldValue) },
        visualTransformation = if (passwordVisible) PasswordVisualTransformation() else VisualTransformation.None,
        keyboardOptions = KeyboardOptions.Default.copy(
            keyboardType = keyboardType,
            imeAction = action
        ),
        textStyle = TextStyle(color = Color.Black, fontWeight = FontWeight.Bold),
        singleLine = !multiLine,
        maxLines = if (multiLine) 5 else 1,
        readOnly = false,
        enabled = enabled,
        colors = TextFieldDefaults.colors(
            focusedTextColor = Color.Blue,
            unfocusedTextColor = Color.Gray,
            disabledTextColor = Color.LightGray,
            errorTextColor = Color.Red,
            focusedContainerColor = Color.LightGray,
            unfocusedContainerColor = Color.LightGray,
            disabledContainerColor = Color.Gray,
            errorContainerColor = Color.Red,
            cursorColor = Color.Blue,
            errorCursorColor = Color.Red,
            selectionColors = TextSelectionColors(
                handleColor = Color.Blue,
                backgroundColor = Color.LightGray.copy(alpha = 0.4f)
            ),
            focusedIndicatorColor = Color.Green,
            unfocusedIndicatorColor = Color.Gray,
            disabledIndicatorColor = Color.LightGray,
            errorIndicatorColor = Color.Yellow,
            focusedLeadingIconColor = Color.Magenta,
            unfocusedLeadingIconColor = Color.Cyan,
            disabledLeadingIconColor = Color.Gray,
            errorLeadingIconColor = Color.Gray,
            focusedTrailingIconColor = Color.Magenta,
            unfocusedTrailingIconColor = Color.Cyan,
            disabledTrailingIconColor = Color.Gray,
            errorTrailingIconColor = Color.Red,
            focusedLabelColor = Color.Magenta,
            unfocusedLabelColor = Color.Gray,
            disabledLabelColor = Color.LightGray,
            errorLabelColor = Color.Red,
            focusedPlaceholderColor = Color.Magenta,
            unfocusedPlaceholderColor = Color.LightGray,
            disabledPlaceholderColor = Color.Gray,
            errorPlaceholderColor = Color.Red,
            focusedSupportingTextColor = Color.Green,
            unfocusedSupportingTextColor = Color.Gray,
            disabledSupportingTextColor = Color.LightGray,
            errorSupportingTextColor = Color.Red,
            focusedPrefixColor = Color.Blue,
            unfocusedPrefixColor = Color.Gray,
            disabledPrefixColor = Color.LightGray,
            errorPrefixColor = Color.Red,
            focusedSuffixColor = Color.Blue,
            unfocusedSuffixColor = Color.Gray,
            disabledSuffixColor = Color.LightGray,
            errorSuffixColor = Color.Red
        )
    )
}