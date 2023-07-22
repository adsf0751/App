簡單的實作retrofit api 撈取 https://api.github.com/search/users?q={query} 和 https://api.github.com/users/{username}/repos 的資料，
使用recyclerview 呈現出每個搜尋的github users 和 特定user的repos，並用livedata的觀察者模式自動更新UI。
