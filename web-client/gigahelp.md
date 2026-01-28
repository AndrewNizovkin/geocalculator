Вот пример реализации такого метода на JavaScript с использованием API `FileSystemAccess` для браузеров, поддерживающих работу с локальной файловой системой устройства:

```javascript
class FileWriter {
  async writeArrayToFile(arrayOfStrings, filePath) {
    try {
      // Запрашиваем доступ к существующему файлу или создаем новый
      const handle = await window.showSaveFilePicker({
        suggestedName: filePath,
        types: [
          {
            description: 'Text Files',
            accept: { 'text/plain': ['.txt'] },
          },
        ],
      });
      
      // Создаем объект FileSystemWritableFileStream
      const writableStream = await handle.createWritable();

      // Преобразуем каждую строку массива в буфер
      for (const str of arrayOfStrings) {
        await writableStream.write(str + '\n');
      }

      // Закрываем поток записи файла
      await writableStream.close();

      console.log('Файл успешно сохранён.');
    } catch (err) {
      console.error(`Ошибка сохранения файла: ${err.message}`);
    }
  }
}

// Пример использования:
(async () => {
  const writer = new FileWriter();
  const data = ["Строка 1", "Строка 2", "Строка 3"];
  await writer.writeArrayToFile(data, "example.txt");
})();
```

### Как работает метод?

1. **showSaveFilePicker**: вызывает диалог выбора файла, позволяющий пользователю выбрать существующий файл или создать новый файл с указанным именем (`filePath`) и типом (`.txt`).
   
2. После подтверждения пользователем создается поток записи (`createWritable`), через который производится запись каждой строки из переданного массива с добавлением символа перевода строки (`\n`).

3. После завершения записи потока закрывается методом `.close()`, а операция завершается выводом уведомления в консоль браузера.

### Примечания

- Этот способ требует поддержки современного браузера (например, Chrome, Edge). Некоторые старые версии браузеров не поддерживают API файлового доступа.
- Пользователь должен подтвердить сохранение файла вручную, иначе ничего не произойдет.

Этот метод удобен для интерактивных приложений, работающих непосредственно с устройством клиента.

```css
.hidden { /* Скрываем модальное окно */
    display: none;
}

#overlay {
    position: fixed;
    top: 0;
    bottom: 0;
    right: 0;
    left: 0;
    background-color: rgba(0, 0, 0, 0.5); /* Полупрозрачность */
    z-index: 1000;
    display: none; /* Изначально скрытый */
}

#modal-popup {
    position: fixed;
    top: 50%;
    left: 50%;
    transform: translate(-50%, -50%); /* Центр экрана */
    background-color: white;
    padding: 20px;
    max-width: 500px;
    min-width: 300px;
    border-radius: 5px;
    box-shadow: 0 0 10px rgba(0, 0, 0, .2);
    z-index: 1001; /* Модальное окно поверх оверлея */
}```