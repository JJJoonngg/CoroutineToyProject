# Coroutine Toy Project

- coroutine을 이용하여 비동기 프로그래밍을 이해하고, 직접 코딩해보며 coroutine scope 에 대한 이해도 등을 올리기 위해 진행한 Toy Project
- Firebase 와의 통신, 화면갱신 사이에 Coroutine 을 사용하여 비동기를 적용
- 간단한 흐름 파악이 목적이기에 Dispathers.Main 수준만 사용
- listener를 활용
- `CustomView`, `Ttablayout & Viewpager`, `FireStore`, `MVP Architecture`





## Coroutine

-  GlobalScope.launch(Dispatchers.Main) 의 사용 예시



### **In CustomFloatingActionButtonUI**

- 해당 버튼 click 시 anim() - animation 효과를 위해 비동기 사용

```kotlin
    fun handlingButtons() {
        GlobalScope.launch(Dispatchers.Main) {
            with(menuButton) {
                anim()
            }
        }
    }
```



### **In TodoPresenter & TodoModel & FireStoreAccessor**

- recycler view 를 초기화 하기 전 FireStore 로 부터 해당하는 데이터를 받아오기까지 `join()` 과 `await()` 를 이용하여 대기하게 한뒤, 해당 Data가 다 받아오게 되면 그것을 화면에 다시 뿌려주는 부분에서의 비동기 사용

```kotlin
//TodoPresenter
override fun initialize() {
        GlobalScope.launch(Dispatchers.Main) {
            TodoRecyclerViewAdapter().let { adapter ->
                adapterView = adapter
                adapterView.itemListener = todoListItemListener
                view.setAdapter(adapter)
            }
            GlobalScope.launch(Dispatchers.Main) {
                datas = TodoModel.readTodoFromFireStore()!!
            }.join()
            adapterView.initItems(datas)
            adapterView.notifyAdapter()
        }
}

//TodoModel
suspend fun readTodoFromFireStore(): MutableList<TodoDTO>? {
        return FireStoreAccessor.readTodo()
}

//FireStoreAccessor   
suspend fun readTodo(): MutableList<TodoDTO> {
        val datas = mutableListOf<TodoDTO>()

        fireStore.collection(TODO)
            .whereEqualTo(type, TODO)
            .get()
            .addOnSuccessListener { items ->
                for (item in items) {
                    val postId = item.data[postId].toString()
                    val title = item.data[title].toString()
                    val content = item.data[content].toString()
                    val date = item.data[date].toString()
                    datas.add(
                        TodoDTO(
                            postId,
                            title,
                            content,
                            date,
                            TODO
                        )
                    )
                }
            }
            .await()

        return datas   
}
```





## ScreenShoot

<img src ="https://user-images.githubusercontent.com/52276038/89001826-49519700-d336-11ea-9eae-983be61c48a0.gif">

