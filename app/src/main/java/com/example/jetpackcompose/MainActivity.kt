package com.example.jetpackcompose

import androidx.compose.ui.tooling.preview.Preview
import android.os.Bundle
import androidx.compose.ui.text.style.TextDecoration
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.KeyframesWithSplineSpec
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.rememberScrollState


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            var currentScreen by remember {
                mutableStateOf("welcome")
            }
            MaterialTheme{
            when (currentScreen) {
                "welcome" -> WelcomeScreen(
                    onStart = {
                        currentScreen = "components"
                    }
                )

                "components" -> ComponentsScreen(
                    onSelect = { selected ->
                        currentScreen = selected }
                )

                else -> DetailsScreen(
                    name = currentScreen,
                    onBack = {
                        currentScreen = "components"
                    }
                )
                }
            }
        }
    }
}
//màn welcome screen

@Composable
fun WelcomeScreen(onStart: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
        //verticalArrangement = Arrangement.SpaceBetween
    ) {
        Spacer(modifier = Modifier.weight(1f))

        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "Logo",
            modifier = Modifier.size(150.dp)
        )
        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "Jetpack Compose",
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.titleLarge,
            fontSize = 22.sp
        )
        Spacer(modifier = Modifier.height(30.dp))

        Text(
            text = "Jetpack Compose is a modern UI toolkit " +
                    "for building native Android applications " +
                    "using a declarative programming approach.",
            fontSize = 16.sp,
            textAlign = TextAlign.Center
        )


        Spacer(modifier = Modifier.weight(1f))

        Button(
            onClick = onStart,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 26.dp),
            shape = RoundedCornerShape(30.dp)
        ) {
            Text("I'm ready")
        }
    }
}


@Composable
fun ComponentsScreen(onSelect: (String) -> Unit) {
    val groups = listOf(
        "Display" to listOf("Text", "Image"),
        "Input" to listOf(
            "TextField", "PasswordField",
            "Toggle", "Slider", "DatePicker"
        ),
        "Layout" to listOf("Column", "Row"),
        "Selection" to listOf(
            "Button", "Checkbox", "RadioButton", "Picker"
        ),
        "Content" to listOf("List", "Card", "Divider"),
        "Navigation" to listOf(
            "NavigationView", "TabBar", "Menu"
        )
        )
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "UI Components List",
            fontSize = 24.sp,
            color = Color(0xFF2196F3)
        )

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            groups.forEach { (group, componentNames) ->
                item {
                    Text(
                        text = group,
                        fontSize = 18.sp
                    )
                }
                items(componentNames) { name ->
                    Card(
                        onClick = { onSelect(name) },
                        modifier = Modifier.fillMaxWidth(),
                        colors = CardDefaults.cardColors(
                            containerColor = Color(0x8ACBFDFF)
                        )
                    ) {
                        Column(
                            modifier = Modifier.padding(16.dp)
                        ) {
                            Text(text = name)
                            Text(
                                text = "View $name component",
                                        fontSize = 12.sp
                            )
                        }
                    }
                }
            }
        }
    }
}
@Composable
fun DetailsScreen(
    name: String,
    onBack: () -> Unit
){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    )
    {
        Spacer(modifier = Modifier.height(20.dp))

        TextButton(onClick = onBack) {
            Text("< Back")
        }
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = "$name Detail",
            fontSize = 24.sp,
            color = Color(0xFF008EFF)
        )
        Spacer(modifier = Modifier.height(20.dp))

        when (name){
            "Text" -> {
                Text(
                    text = buildAnnotatedString { //tạo một đoạn chữ có nhiều kiểu khác nhau
                        append("The ") //append là thêm chữ vào câu

                        withStyle( //áp dụng một kiểu chữ
                            SpanStyle( // nơi khai báo kiểu chữ
                                textDecoration = TextDecoration.LineThrough
                            )
                        ) {
                            append(" quick")
                        }


                        withStyle(
                                SpanStyle(
                                    color = Color(0xFFB8860B),
                                    fontSize = 35.sp
                                )
                            ){
                            append(" Brown")
                        }
                        append(" fox")

                        withStyle(
                            SpanStyle(
                                letterSpacing = 5.sp
                        )
                        ){
                            append(" jumps")
                        }
                        //đầu
                        withStyle(
                            SpanStyle(
                                fontWeight = FontWeight.Bold //chỉnh độ đậm
                            )
                        ){
                            append(" over")
                        }
                        //cuối withStyle > SpanStyle > append
                        withStyle(
                            SpanStyle(
                                textDecoration = TextDecoration.Underline
                            )
                        ){
                            append(" the")
                        }

                        withStyle(
                            SpanStyle(
                                fontStyle = FontStyle.Italic
                            )
                        ){
                            append(" lazy")
                        }
                        append(" dog.")
                    },
                    fontSize = 20.sp,
                    lineHeight= 35.sp
                )

                Spacer(modifier = Modifier.height(10.dp))

                Text(
                        text = buildAnnotatedString {
                            append("The ")

                            withStyle(
                                SpanStyle(
                                    fontStyle = FontStyle.Italic,
                                    textDecoration = TextDecoration.LineThrough
                                )
                            ){
                                append(" black")
                            }

                            withStyle(
                                SpanStyle(
                                    fontWeight = FontWeight.ExtraBold
                                )
                            ){
                                append(" cat")
                            }

                            withStyle(
                                SpanStyle(
                                    fontSize = 30.sp
                                )
                            ){
                                append(" climbed")
                            }

                            withStyle(
                                SpanStyle(
                                    letterSpacing = 5.sp
                                )
                            ){
                                append(" onto a")
                            }

                            withStyle(
                                SpanStyle(
                                    textDecoration = TextDecoration.Underline
                                )
                            ){
                                append(" high")
                            }

                            withStyle(
                                SpanStyle(
                                    fontStyle = FontStyle.Italic
                                )
                            ){
                                append(" branch.")
                            }
                        },
                                fontSize= 20.sp,
                                lineHeight= 35.sp
                        )

            }
            "TextField" -> {
                var value by remember {
                    mutableStateOf("")
                }

                OutlinedTextField(
                    value = value,
                    onValueChange = { value = it},
                    label = { Text("Thông tin nhập")}
                )

                Spacer(modifier = Modifier.height(12.dp))
                Text("Bạn đã nhập: $value")
            }
            "Row" -> {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ){
                    repeat(3){ index ->
                        Card(
                            modifier = Modifier
                            .weight(1f)
                            .height(60.dp),
                            colors = CardDefaults.cardColors(
                            containerColor = Color(0xFF90CAF9)
                        )
                        ){
                            Box(
                                modifier = Modifier.fillMaxSize(),
                                contentAlignment = Alignment.Center
                            ) {
                                Text("${index + 1}")
                            }
                        }
                    }
                }
            }
            "Column" -> {
                Column(
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    repeat(3){ index ->
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()//mở ô rộng hết khung
                                .height(60.dp),
                            colors = CardDefaults.cardColors(
                                containerColor = Color(0xFF90CAF9)
                            )
                        ){
                            Box( //đặt số ở giữa ô
                                modifier = Modifier.fillMaxSize(),
                                contentAlignment = Alignment.Center
                            ){
                                Text("${index + 1}")// hiển thị lần lượt 1,2,3
                            }
                        }
                    }
                }
            }
            "Image" -> {
                    Text(
                        text = "Hình ảnh minh họa: ",
                        fontSize = 25.sp
                )
                Spacer(modifier = Modifier.height(24.dp))

                Image(
                    painter = painterResource(id = R.drawable.fox),
                    contentDescription = "Fox",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(300.dp),
                    contentScale = ContentScale.Fit
                )
            }
            "PasswordField" ->{
                var password by remember {
                    mutableStateOf("")
                }
                OutlinedTextField(
                    value = password,
                    onValueChange = {password =it},
                    label = {Text("Password")},
                    visualTransformation = PasswordVisualTransformation()
                    // người dùng nhập chữ bình thường, màn hình hiển thị dấu chấm để che
                )
            }
            "Toggle" -> {
                var isChecked by remember{
                    mutableStateOf(false)
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ){
                    Text("Bật thông báo")

                    Spacer(modifier = Modifier.width(20.dp))

                    Switch( //tạo công tắc
                        checked = isChecked,//kiểm tra xem công tắt bật hay tắt
                        onCheckedChange = { //giả dụ người dùng
                            isChecked = it//cập nhật trạng thái
                        }
                    )
                }
                Text(
                    text = if(isChecked) "Đang bật" else "Đang tắt"
                )
            }
            "Slider" -> { // tạo thanh kéo
                var sliderValue by remember{ // cho gi trị hiện tại
                    mutableFloatStateOf(40f)
                }
                Text(
                    text = "Giá trị: ${sliderValue.toInt()}%"//chuyển số thập phân thành số nguyên
                )

                Slider(
                    value = sliderValue,
                    onValueChange = {// khi kéo thì cập nhật giá trị
                        sliderValue = it
                    },
                    valueRange = 0f..100f//kéo từ 0 đến 100
                )
            }
            "DatePicker" -> {
                var showDialog by remember { //quyết định lịch có hiện hay ko
                    mutableStateOf(false)
                }

                val dateState = rememberDatePickerState()//lưu ngày người dùng chọn

                Button(onClick = {//nút mở lịch
                    showDialog = true
                }) {
                    Text("Chọn ngày")
                }

                if (showDialog) {//chỉ hiện lịch khi biến bằng true
                    DatePickerDialog(//hộp thoại chứa lịch
                        onDismissRequest = {
                            showDialog = false
                        },
                        confirmButton = {
                            TextButton(
                                onClick = {
                                    showDialog = false
                                }
                            ) {
                                Text("OK")
                            }
                        },
                        dismissButton = {
                            TextButton(
                                onClick = {
                                    showDialog = false
                                }
                            ) {
                                Text("Cancel")
                            }
                        }
                    ) {
                        DatePicker(state = dateState)//lịch để chọn ngày
                    }
                }

                Text(
                    text = "Ngày đã chọn: " +
                            (dateState.selectedDateMillis//ngày được chọn dưới dạng mili giây
                                ?.let {
                                    java.text.SimpleDateFormat(
                                        "dd/MM/yyyy",
                                        java.util.Locale.getDefault()
                                    ).format(java.util.Date(it))
                                } ?: "Chưa chọn")
                )
            }
            "Button" -> {
                var count by remember {
                    mutableIntStateOf(0)
                }

                Button(
                    onClick = {
                        count++
                    }
                ) {
                    Text("Click me!")
                }

                Text("Đã nhấn: $count lần")
            }
            "Checkbox" -> {
                var checked by remember {
                    mutableStateOf(false)
                }

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Checkbox(
                        checked = checked,
                        onCheckedChange = {
                            checked = it
                        }
                    )

                    Text("Đồng ý điều khoản")
                }

                Text(
                    text = if (checked)
                        "Đã đồng ý"
                    else
                        "Chưa đồng ý"
                )
            }
            "RadioButton" -> {
                var selected by remember {
                    mutableStateOf("Kotlin")
                }

                val options = listOf("Kotlin", "Java", "Python")

                Column {
                    options.forEach { option ->
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            RadioButton(
                                selected = (selected == option),
                                onClick = {
                                    selected = option
                                }
                            )

                            Text(text = option)
                        }
                    }

                    Text("Bạn đã chọn: $selected")
                }
            }
            "Picker" -> {
                var expanded by remember {
                    mutableStateOf(false)
                }

                var selectedFruit by remember {
                    mutableStateOf("Táo")
                }

                val fruits = listOf("Táo", "Cam", "Xoài", "Dâu")

                Box {
                    OutlinedButton(
                        onClick = {
                            expanded = true
                        }
                    ) {
                        Text(selectedFruit)
                    }

                    DropdownMenu(
                        expanded = expanded,
                        onDismissRequest = {
                            expanded = false
                        }
                    ) {
                        fruits.forEach { fruit ->
                            DropdownMenuItem(
                                text = { Text(fruit) },
                                onClick = {
                                    selectedFruit = fruit
                                    expanded = false
                                }
                            )
                        }
                    }
                }

                Text("Bạn đã chọn: $selectedFruit")
            }
            "List" -> {
                val students = listOf(
                    "Nguyễn Văn An",
                    "Trần Thị Bình",
                    "Lê Minh Khang"
                )

                Column(
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    students.forEach { student ->
                        Card(
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(
                                text = student,
                                modifier = Modifier.padding(16.dp)
                            )
                        }
                    }
                }
            }
            "Card" -> {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = Color(0xFFE3F2FD)
                    )
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp)
                    ) {
                        Text(
                            text = "Nguyễn Văn An",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        Text("Sinh viên Công nghệ thông tin")
                        Text("MSSV: 12345678")
                        Text("Lớp: CNTT01")
                    }
                }
            }
            "Divider" -> {
                Column {
                    Text("Thông tin cá nhân")

                    HorizontalDivider(
                        modifier = Modifier.padding(vertical = 16.dp),
                        thickness = 2.dp,
                        color = Color.Gray
                    )

                    Text("Thông tin học tập")

                    HorizontalDivider(
                        modifier = Modifier.padding(vertical = 16.dp),
                        thickness = 2.dp,
                        color = Color.Gray
                    )

                    Text("Thông tin liên hệ")
                }
            }
            "NavigationView" -> {
                var selectedItem by remember {
                    mutableStateOf("Home")
                }

                val navItems = listOf("Home", "Search", "Profile")

                Column {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(150.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text("$selectedItem Screen")
                    }

                    NavigationBar {
                        navItems.forEach { item ->
                            NavigationBarItem(
                                selected = selectedItem == item,
                                onClick = {
                                    selectedItem = item
                                },
                                icon = {
                                    Text(
                                        when (item) {
                                            "Home" -> "⌂"
                                            "Search" -> "⌕"
                                            else -> "●"
                                        }
                                    )
                                },
                                label = { Text(item) }
                            )
                        }
                    }
                }
            }
            "TabBar" -> {
                var selectedTab by remember {
                    mutableIntStateOf(0)
                }

                val tabs = listOf("Thông tin", "Hình ảnh", "Video")

                Column {
                    PrimaryTabRow(
                        selectedTabIndex = selectedTab
                    ) {
                        tabs.forEachIndexed { index, title ->
                            Tab(
                                selected = selectedTab == index,
                                onClick = {
                                    selectedTab = index
                                },
                                text = { Text(title) }
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(30.dp))

                    Text(
                        text = "Nội dung tab ${tabs[selectedTab]}"
                    )
                }
            }
            "Menu" -> {
                var expanded by remember {
                    mutableStateOf(false)
                }

                var selected by remember {
                    mutableStateOf("Chưa chọn")
                }

                Box {
                    TextButton(
                        onClick = {
                            expanded = true
                        }
                    ) {
                        Text("⋮ Menu")
                    }

                    DropdownMenu(
                        expanded = expanded,
                        onDismissRequest = {
                            expanded = false
                        }
                    ) {
                        listOf("Chỉnh sửa", "Chia sẻ", "Xóa")
                            .forEach { option ->
                                DropdownMenuItem(
                                    text = { Text(option) },
                                    onClick = {
                                        selected = option
                                        expanded = false
                                    }
                                )
                            }
                    }
                }

                Text("Lựa chọn: $selected")
            }

        }
    }
}

@Preview(
    showBackground = true,
    showSystemUi = true,
    name = "Welcome Screen"
)

@Composable
fun WelcomeScreenPreview(){
    MaterialTheme{
        WelcomeScreen ( onStart = {})
    }
}

@Preview(
    showBackground = true,
    name = "Text Detail"
)

@Composable
fun TextDetailPreview(){
    MaterialTheme{
        DetailsScreen(
            name = "Text",
            onBack = {}
        )
    }
}

@Preview(
    showBackground = true,
    name = "Components List"
)

@Composable
fun ComponentsScreenPreview(){
    MaterialTheme {
        ComponentsScreen (onSelect = {})
    }
}

