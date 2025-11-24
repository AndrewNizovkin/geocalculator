    function pow(x, n) {
      /* Здесь будет реализация функции, пока пусто */
      if (n == 0) {
        return 1;
      }
      let result = x;
      for (let i = 1; i < n; i++) {
        result *= x;
      }
      return result;
    }

    function add(x, y) {
        return x +y;
    }
