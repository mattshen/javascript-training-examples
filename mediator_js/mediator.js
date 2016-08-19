var Display = function Display(displayId) {
    var display = $('#'+displayId);
    return {
        setText: function(text) {
            display.text(text);
        }
    };
}

var Button = function Button(buttonId, businessLogicHandler) {
    var button = $('#'+buttonId);

    button.click(function(){
        if (businessLogicHandler) businessLogicHandler();
    });

    return {
        enable : function enable() {
            button.prop('disabled', false);
        },
        disable : function disable() {
            button.prop('disabled', true);
        },
        bindUIStatusHandler: function(handler) {
            button.click(function(){
                if (handler) handler();
            });
        }
    };
};

var Mediator = function Mediator(display, buttons){

    var viewHandler = function(){
        display.setText('viewing...');
        buttons.view.disable();
        buttons.book.enable();
        buttons.search.enable();
    }

    buttons.view.bindUIStatusHandler(viewHandler);

    var bookHandler = function(){
        display.setText('booking...');
        buttons.view.enable();
        buttons.book.disable();
        buttons.search.enable();
    }
    
    buttons.book.bindUIStatusHandler(bookHandler);

    var searchHandler = function(){
        display.setText('search...');
        buttons.view.enable();
        buttons.book.enable();
        buttons.search.disable();
    };

    buttons.search.bindUIStatusHandler(searchHandler);

    return {
        viewHandler: viewHandler,
        bookHandler: bookHandler,
        searchHandler: searchHandler
    };

};
