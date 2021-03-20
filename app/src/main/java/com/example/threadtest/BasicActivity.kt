package com.example.threadtest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.threadtest.myutil.TestThread01

/*
main() {


.....;
.....;
.....;
A.aa();
.....;
.....;
B.bb();

}

쭉 ~ 그리는거
선이 하나면 싱글 스레드

    <동기(Synchronize)와 비동기(Asynchronize)>
    멀티스레드 : 비동기
    1) Thread, Runnable(From Java)
    2) Handler
        - 작업 스레드를 저장한 Queue가 있다.
        - 각 노드는 what, message로 구성되어 있다.
        - what: 표시용 정수(int)
        message : what, arg1, arg2, Object(표시용 정수 + 세부적인 데이터)
            sendEmptyMessage(int what)
            sendMessage(int what, Message message)
            sendEmptyDelayed(int what, long millis)
            removeMessage(int what)
            handleMessage(Message message)

    3) AsyncTask
        - 서버와 통신을 해야 할 때 사용
        (통신은 메인스레드에서 하면 안됨)
        - 요즘엔 Retrofit2 이라는 라이브러리를 사용한다.(국룰)
        - doInBackground() <- 필수 오버라이드
                : 처리하려는 작업을 여기에 작성한다.
        - onProgressUpdate()
                : 중간 중간 보고할 내용을 여기에 작성한다. (로딩바, 퍼센트 증가 등)
       - onPostExecute()
                :doInBackgroud()가 종료되면 실행할 마무리 작업을 여기에 작성한다.

    3)Corountine(코루틴)

    <Thread>
    - 하나의 프로세스가 처리하는 작업 단위
    - '실' 이라고 부른다 --> 작업의 흐름을 선으로 그었을 때 모양이 실 같아서서
    - 싱글 스레드 : 흐름이 1개다. 이작업이 수행되는 동안 다른 작업은 수행 불가.
    - 멀티 스레드 : 흐름이 여러개다. 각 스레드 작업들을 프로세서가 번갈아 가며 수행 한다. (병렬 처리)
                    -> 동시에 여러작업이 가능하다.

    - 안드로이드에서 스레드의 주 용도
    1. 뷰를 담당하는 메인 스레드가 있음 (oncreate() 부터 ondestroy() 까지를 담당하는 스레드)
    2. 백그라운드 스레드 : 메인 스레드에 종속되어 뒤에서 돌아가는 스레드
            1) 이벤트 리스너 (onClickListener onTouchListener등 익명함수들) -> 이벤트가 발생할 것을 주시하는 스레드
            2) 통신(http/s) -> 아주 중요!!! 통신은 메인스레드에서 작업하면 에러가 난다. 반드시 백그라운드로 작업해야 한다. 왜? 메인스레드는 뷰 담당이니까! 사용자와
                               가장 가깝기 때문에 불편함을 느끼지 않게 해야함. 메인에서 돌릴경우 다운로드 작업이 완료될때 까지 화면 터치도 못하고,, 기다려야하니껜..
                               예전에는 가능했지만 이제는 메인스레드에서 실행 시 app 이 꺼져버륌~

    <스레드를 추가하는 방법>
    1. 순수 자바의 방법
        - java.lang.Thread 클래스, java.lang.Runnable 클래스 중 둘 중 하나를 상속받아서 사용한다.

 */

class BasicActivity : AppCompatActivity() {

    lateinit var button: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_basic)

        val thread1 = TestThread01()

        button = findViewById(R.id.btn)
        button.setOnClickListener {
            val th = Thread(thread1)
            th.start()
        }
    }
}