package com.a2.swifting_fitness.screens.compose

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.a2.swifting_fitness.R


@Composable
fun CustomTextField(
    modifier: Modifier = Modifier,
    value: String,
    label: String,
    onValueChange: (String) -> Unit,
    enabled: Boolean = true,
    multiLine: Boolean = false,
    keyboardType: KeyboardType = KeyboardType.Text,
    action: ImeAction = ImeAction.Next,
    showError:Boolean,
    errorIfInvalid:String?=null,
    isPassword: Boolean = false,
    trailingIcon: @Composable (() -> Unit)? = null,
    leadingIcon: @Composable (() -> Unit)
) {

    val (passwordVisible, setPasswordVisible) = remember { mutableStateOf(isPassword) }
    Column {
        Box(
            modifier = modifier
                .fillMaxWidth()
                .background(color = Color(0x11FFFFFF), shape = RoundedCornerShape(12.dp)),
        ) {
            OutlinedTextField(
                isError = errorIfInvalid != null && showError,
                modifier = modifier
                    .fillMaxWidth(),
                label = { Text(label, color = Color.White) },
                value = value,
                leadingIcon = leadingIcon,
                trailingIcon = {
                    trailingIcon?.invoke()
                    if (isPassword) {
                        val image =
                            if (passwordVisible) R.drawable.solid_eye else R.drawable.app_logo
                        val description = if (passwordVisible) "Hide password" else "Show password"
                        Image(
                            contentDescription = description,
                            painter = painterResource(image),
                            modifier = Modifier
                                .clickable {
                                    setPasswordVisible(!passwordVisible)
                                }
                                .size(30.dp)

                        )

                    }
                },
                onValueChange = { textFieldValue -> onValueChange(textFieldValue) },
                visualTransformation = if (passwordVisible) PasswordVisualTransformation() else VisualTransformation.None,
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = keyboardType,
                    imeAction = action
                ),
                textStyle = TextStyle(color = Color.White, fontWeight = FontWeight.Bold),
                singleLine = !multiLine,
                maxLines = if (multiLine) 5 else 1,
                readOnly = false,
                enabled = enabled,

                colors = TextFieldDefaults.colors(
                    focusedTextColor = Color.Blue,
                    unfocusedTextColor = Color.Transparent,
                    disabledTextColor = Color.Transparent,
                    errorTextColor = Color.Red,
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent,
                    disabledContainerColor = Color.Transparent,
                    errorContainerColor = Color.Transparent,
                    cursorColor = Color.Blue,
                    errorCursorColor = Color.Red,
                    selectionColors = TextSelectionColors(
                        handleColor = Color.Blue,
                        backgroundColor = Color.Transparent.copy(alpha = 0.1f)
                    ),
                    focusedIndicatorColor = Color.Gray,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent,
                    errorIndicatorColor = Color.Transparent,
                    focusedLeadingIconColor = Color.White,
                    unfocusedLeadingIconColor = Color.White,
                    disabledLeadingIconColor = Color.White,
                    errorLeadingIconColor = Color.White,
                    focusedTrailingIconColor = Color.Magenta,
                    unfocusedTrailingIconColor = Color.Cyan,
                    disabledTrailingIconColor = Color.Transparent,
                    errorTrailingIconColor = Color.Red,
                    focusedLabelColor = Color.Magenta,
                    unfocusedLabelColor = Color.Transparent,
                    disabledLabelColor = Color.Transparent,
                    errorLabelColor = Color.Red,
                    focusedPlaceholderColor = Color.Magenta,
                    unfocusedPlaceholderColor = Color.Transparent,
                    disabledPlaceholderColor = Color.Transparent,
                    errorPlaceholderColor = Color.Red,
                    focusedSupportingTextColor = Color.Gray,
                    unfocusedSupportingTextColor = Color.Transparent,
                    disabledSupportingTextColor = Color.Transparent,
                    errorSupportingTextColor = Color.Red,
                    focusedPrefixColor = Color.Blue,
                    unfocusedPrefixColor = Color.Transparent,
                    disabledPrefixColor = Color.Transparent,
                    errorPrefixColor = Color.Red,
                    focusedSuffixColor = Color.Blue,
                    unfocusedSuffixColor = Color.Transparent,
                    disabledSuffixColor = Color.Transparent,
                    errorSuffixColor = Color.Red
                )
            )
        }

        if (errorIfInvalid!=null && showError) {
            Text(text = errorIfInvalid, color = Color.Red)
        }
    }
}