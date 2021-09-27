package com.gdsc.instagramprofileui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.rounded.GridView
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.ui.*
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@ExperimentalFoundationApi
@Composable
fun ProfileScreen() {
    // 선택된 탭의 인덱스 저장하기
    var selectedTabIndex by remember {
        mutableStateOf(0)
    }

    Column(modifier = Modifier.fillMaxSize()) {
        TopBar(
            name = "leeeha",
            modifier = Modifier.padding(10.dp)
        )
        Spacer(modifier = Modifier.height(4.dp))
        ProfileSection()
        Spacer(modifier = Modifier.height(25.dp))
        ButtonSection(modifier = Modifier.fillMaxWidth())
        Spacer(modifier = Modifier.height(25.dp))
        HighlightSection(
            highlights = listOf(
                ImageWithText(
                    image = painterResource(id = R.drawable.youtube),
                    text = "Youtube"
                ),
                ImageWithText(
                    image = painterResource(id = R.drawable.qa),
                    text = "Q&A"
                ),
                ImageWithText(
                    image = painterResource(id = R.drawable.discord),
                    text = "Discord"
                ),
                ImageWithText(
                    image = painterResource(id = R.drawable.telegram),
                    text = "Telegram"
                )
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
        )
        Spacer(modifier = Modifier.height(10.dp))

        PostTabView(
            imageWithTexts = listOf(
                ImageWithText(
                    //image = painterResource(id = R.drawable.ic_grid),
                    // build.gradle에 라이브러리 추가해서 편하게 아이콘 사용하기
                    image = rememberVectorPainter(image = Icons.Rounded.GridView),
                    text = "Posts"
                ),
                ImageWithText(
                    image = painterResource(id = R.drawable.ic_reels),
                    text = "Reels"
                ),
                ImageWithText(
                    image = painterResource(id = R.drawable.ic_igtv),
                    text = "IGTV"
                ),
                ImageWithText(
                    image = painterResource(id = R.drawable.profile),
                    text = "Profile"
                ),
            )
        ){ // onTabSelected 함수의 매개변수 초기화
            selectedTabIndex = it

            // 코틀린에서 마지막 argument가 함수일 때는 {}을 밖으로 빼내어 코드를 작성한다.
            // 대표적으로 Row(){}에서도 {}안의 내용이 argument이다.
        }
        when (selectedTabIndex) {
            0 -> PostSection(
                posts = listOf( // List<Painter>
                    painterResource(id = R.drawable.peanuts1),
                    painterResource(id = R.drawable.peanuts2),
                    painterResource(id = R.drawable.peanuts3),
                    painterResource(id = R.drawable.peanuts4),
                    painterResource(id = R.drawable.peanuts5),
                    painterResource(id = R.drawable.peanuts6),
                ),
                modifier = Modifier.fillMaxWidth() // match_parent
            )
        }
    }
}

@Composable
fun TopBar(
    name: String,
    modifier: Modifier = Modifier // 밖에서 함수를 호출할 때, 원하는 modifier를 설정하기 위해서
) {
    Row(
        // Alignment는 정렬 기준 설정, Arrangement는 요소들의 간의 배치
        // https://developer.android.com/reference/kotlin/androidx/compose/foundation/layout/Arrangement
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceAround,
        modifier = modifier
            .fillMaxWidth() // 매개변수로 전달 받은 modifier에 이어서 사용함. (체이닝)
    ) {
        Icon(
            imageVector = Icons.Default.ArrowBack, // 뒤로가기
            contentDescription = "Back",
            tint = Color.Black,
            modifier = Modifier.size(24.dp)
        )
        Text(
            text = name,
            // 텍스트가 최대 너비를 벗어나면 뒤에는 ...으로 생략해서 표기함.
            overflow = TextOverflow.Ellipsis, // 생략 부호 ellipsis
            fontWeight = FontWeight.Bold,
            // sp는 사용자가 시스템에서 설정해둔 폰트 사이즈 기준으로 크기를 설정함.
            fontSize = 20.sp
        )
        Icon(
            painter = painterResource(id = R.drawable.ic_bell), // 알림
            contentDescription = "Back",
            tint = Color.Black,
            modifier = Modifier.size(24.dp)
        )
        Icon(
            painter = painterResource(id = R.drawable.ic_dotmenu), // 메뉴
            contentDescription = "Back",
            tint = Color.Black,
            modifier = Modifier.size(20.dp)
        )
    }
}

@Composable
fun ProfileSection(
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.fillMaxWidth()) {
        // RoundImage, StatSection 수평으로 배치
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
        ) {
            RoundImage(
                image = painterResource(id = R.drawable.charlie_brown),
                modifier = Modifier
                    .size(100.dp) // 이미지 크기 설정
                    .weight(3f) // 전체 너비의 30퍼센트 차지
            )
            Spacer(modifier = Modifier.width(16.dp))
            StatSection(modifier = Modifier.weight(7f)) // 전체 너비의 70퍼센트 차지
        }
        Spacer(modifier = Modifier.height(10.dp))

        // 프로필 설명란
        ProfileDescription(
            displayName = "Computer engineering undergraduate student",
            description = "Seoul National University of Science and Technology\n" +
                    "22 years old\n" +
                    "I'm interested in developing Android apps!",
            url = "https://github.com/leeeha",
            followedBy = listOf("seoultech.offical", "seoultech_com", "gdsc_seoultech"),
            otherCount = 17
        )
    }
}

@Composable
fun RoundImage( // 재사용을 위해 함수로 모듈화
    image: Painter,
    modifier: Modifier = Modifier
) {
    Image(
        painter = image,
        contentDescription = null,
        modifier = modifier
            .aspectRatio(1f, matchHeightConstraintsFirst = true)
            // size to height constraints before trying the width correspondents.
            // 높이를 먼저 설정하고, ratio에 맞춰서 너비 설정 (false일 경우 너비 먼저 설정)
            .border(
                width = 1.dp,
                color = Color.LightGray,
                shape = CircleShape
            )
            .padding(3.dp) // 회색 테두리와 이미지 사이의 안쪽 여백 만들기
            .clip(CircleShape) // 실제 이미지를 원형으로 만들기
    )
}

@Composable
fun StatSection(modifier: Modifier = Modifier) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceAround,
        modifier = modifier
    ) {
        // Posts, Followers, Following 수평으로 배치
        ProfileStat(numberText = "601", text = "Posts")
        ProfileStat(numberText = "100K", text = "Followers")
        ProfileStat(numberText = "72", text = "Following")
    }
}

@Composable
fun ProfileStat(
    numberText: String,
    text: String,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        Text(
            text = numberText,
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(text = text)
    }
}

@Composable
fun ProfileDescription(
    displayName: String,
    description: String,
    url: String,
    followedBy: List<String>,
    otherCount: Int
) {
    val letterSpacing = 0.5.sp // 글자 간격
    val lineHeight = 20.sp // 행 간격
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
    ) {
        Text(
            text = displayName,
            fontWeight = FontWeight.Bold,
            letterSpacing = letterSpacing,
            lineHeight = lineHeight
        )
        Text(
            text = description,
            letterSpacing = letterSpacing,
            lineHeight = lineHeight
        )
        Text(
            text = url,
            color = Color(0xFF3D3D91),
            letterSpacing = letterSpacing,
            lineHeight = lineHeight
        )
        if (followedBy.isNotEmpty()) { // followedBy 리스트가 비어 있지 않으면
            Text(
                // StringBuilder 이용해서 부분적으로 스타일 적용하기
                text = buildAnnotatedString {
                    val boldStyle = SpanStyle( // 부분적으로 적용할 스타일 지정
                        color = Color.Black,
                        fontWeight = FontWeight.Bold
                    )

                    append("Followed by ")

                    // followedBy 리스트의 모든 아이템을 순회하면서
                    // 현재의 인덱스는 index에, followedBy[index]는 name에 저장됨.
                    followedBy.forEachIndexed { index, name ->
                        pushStyle(boldStyle) // 볼드체 적용
                        append(name) // name에 볼드체 적용
                        pop() // 볼드체 적용 해제
                        // 마지막 아이템이 아니면 ,를 붙인다.
                        if (index < followedBy.size - 1) {
                            append(", ")
                        }
                    }

                    if (otherCount > 2) { // 2명보다 많으면
                        append(" and ")
                        pushStyle(boldStyle) // and 뒤부터 볼드체 적용
                        append("$otherCount others")
                    }
                },
                letterSpacing = letterSpacing,
                lineHeight = lineHeight
            )
        }
    }
}

@Composable
fun ButtonSection(
    modifier: Modifier = Modifier
) {
    val minWidth = 95.dp
    val height = 30.dp
    Row(
        horizontalArrangement = Arrangement.SpaceEvenly,
        modifier = modifier
    ) {
        ActionButton( // 텍스트와 아이콘 둘다 있는 경우
            text = "Following",
            icon = Icons.Default.KeyboardArrowDown, // 아래 방향의 화살표
            modifier = Modifier
                // 최소 너비 지정 (text가 이 너비보다 길어지면 그에 맞게 ActionButton도 길어짐.
                .defaultMinSize(minWidth = minWidth)
                .height(height)
        )
        ActionButton( // 텍스트만
            text = "Messaged",
            modifier = Modifier
                .defaultMinSize(minWidth = minWidth)
                .height(height)
        )
        ActionButton( // 텍스트만
            text = "Email",
            modifier = Modifier
                .defaultMinSize(minWidth = minWidth)
                .height(height)
        )
        ActionButton( // 아이콘만
            icon = Icons.Default.KeyboardArrowDown,
            modifier = Modifier
                .height(height)
        )
    }
}

@Composable
fun ActionButton(
    modifier: Modifier = Modifier,
    text: String? = null,
    icon: ImageVector? = null
    // Null can not be a value of a non-null type String
    // 타입 뒤에 ?를 붙여줘야 에러가 사라진다.
) {
    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .border( // 버튼의 테두리 설정
                width = 1.dp,
                color = Color.LightGray,
                shape = RoundedCornerShape(5.dp) // 테두리를 둥글게
            )
            .padding(6.dp)
    ) {
        if (text != null) { // 전달받은 text가 null이 아닐 때
            Text( // Text 추가
                text = text,
                fontWeight = FontWeight.SemiBold,
                fontSize = 14.sp
            )
        }
        if (icon != null) { // 전달받은 icon이 null이 아닐 때
            Icon( //  Icon 추가
                imageVector = icon,
                contentDescription = null,
                tint = Color.Black
            )
        }
    }
}

@Composable
fun HighlightSection( // 스토리 하이라이트 부분
    modifier: Modifier = Modifier,
    highlights: List<ImageWithText> // 데이터 클래스 ImageWithText에서 이미지와 텍스트 가져오기
) {
    LazyRow(modifier = modifier) {
        items(highlights.size) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .padding(end = 15.dp)
            ) {
                RoundImage(
                    image = highlights[it].image,
                    modifier = Modifier.size(70.dp)
                )
                Text(
                    text = highlights[it].text,
                    overflow = TextOverflow.Ellipsis,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}

@Composable
fun PostTabView(
    modifier: Modifier = Modifier,
    imageWithTexts: List<ImageWithText>,
    onTabSelected: (selectedIndex: Int) -> Unit
    // (매개변수, 매개변수 타입) -> 리턴 타입 (Unit은 void형)
    // 함수 자체를 매개변수로 받는다!
) {

    // 현재 선택된 탭의 인덱스 저장해두기
    var selectedTabIndex by remember {
        mutableStateOf(0)
    }
    // Type 'TypeVariable(T)' has no method 'getValue(Nothing?, KProperty<*>)'
    // and thus it cannot serve as a delegate
    // --> import androidx.compose.runtime.setValue
    // --> import androidx.compose.runtime.getValue

    // var은 mutable(변경 가능), val은 immutable(변경 불가, read-only)
    val inactiveColor = Color(0xFF777777)

    TabRow( // 탭 아이템들을 가로로 배치
        selectedTabIndex = selectedTabIndex,
        backgroundColor = Color.Transparent,
        contentColor = Color.Black,
        modifier = modifier
    ) {
        imageWithTexts.forEachIndexed { index, item ->
            Tab(
                selected = (selectedTabIndex == index), // 현재 인덱스의 선택 여부 (true, false)
                selectedContentColor = Color.Black, // 선택된 탭은 검정색
                unselectedContentColor = inactiveColor, // 선택되지 않은 탭은 회색
                onClick = { // 클릭한 탭의 인덱스를 selectedTabIndex로 설정
                    selectedTabIndex = index
                    onTabSelected(index) // onTabSelected 함수를 호출한 쪽에서 로직을 처리함.
                }
            ) {
                Icon(
                    painter = item.image,
                    contentDescription = item.text,
                    // 선택된 탭이면 아이콘 색상이 검정색, 아니면 회색
                    tint = if (selectedTabIndex == index) Color.Black else inactiveColor,
                    modifier = Modifier
                        .padding(10.dp)
                        .size(20.dp)
                )
            }
        }
    }
}

@ExperimentalFoundationApi
@Composable
fun PostSection(
    posts: List<Painter>,
    modifier: Modifier = Modifier
) {
    LazyVerticalGrid( // 리스트를 격자 무늬로 보여줌.
        cells = GridCells.Fixed(3), // 3칸짜리
        modifier = modifier
            // 그리드를 1% 확대해서 끝부분에는 이미지의 흰색 테두리가 안 보이도록
            .scale(1.01f)
    ) {
        items(posts.size) {
            Image(
                painter = posts[it],
                contentDescription = null,
                contentScale = ContentScale.Crop, // ratio에 맞지 않는 부분 잘라냄.
                modifier = Modifier
                    .aspectRatio(1f) // 가로 세로 1:1 비율
                    .border( // 이미지에 흰색 테두리 적용
                        width = 1.dp,
                        color = Color.White
                    )
            )
        }
    }
}