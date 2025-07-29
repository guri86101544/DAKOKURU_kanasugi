function updateClock() {
      
      const now = new Date();
      
      const year = now.getFullYear();
      const month = String(now.getMonth() + 1).padStart(2, '0');
      const day =String(now.getDate()).padStart(2,'0');
      
      const weekdays = ['日', '月', '火', '水', '木', '金', '土'];
      const weekday = weekdays[now.getDay()];
      
      const hours = String(now.getHours()).padStart(2, '0');
      const minutes = String(now.getMinutes()).padStart(2, '0');
      const seconds = String(now.getSeconds()).padStart(2, '0');
      
      const dateString = ` ${year}/${month}/${day} (${weekday})`;
      const timeString = ` ${hours}:${minutes}:${seconds}`;


      document.getElementById('clock').textContent =  `${dateString} ${timeString}`;  }

    setInterval(updateClock, 1000); // 1秒ごとに更新
    updateClock(); // 初回表示
