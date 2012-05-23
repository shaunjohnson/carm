CKEDITOR.config.toolbar = 'Custom';

CKEDITOR.config.toolbar_Full =
    [
        { name: 'document', items : [ 'Source','-','Save','NewPage','DocProps','Preview','Print','-','Templates' ] },
        { name: 'clipboard', items : [ 'Cut','Copy','Paste','PasteText','PasteFromWord','-','Undo','Redo' ] },
        { name: 'editing', items : [ 'Find','Replace','-','SelectAll','-','SpellChecker', 'Scayt' ] },
        { name: 'forms', items : [ 'Form', 'Checkbox', 'Radio', 'TextField', 'Textarea', 'Select', 'Button', 'ImageButton',
            'HiddenField' ] },
        '/',
        { name: 'basicstyles', items : [ 'Bold','Italic','Underline','Strike','Subscript','Superscript','-','RemoveFormat' ] },
        { name: 'paragraph', items : [ 'NumberedList','BulletedList','-','Outdent','Indent','-','Blockquote','CreateDiv',
            '-','JustifyLeft','JustifyCenter','JustifyRight','JustifyBlock','-','BidiLtr','BidiRtl' ] },
        { name: 'links', items : [ 'Link','Unlink','Anchor' ] },
        { name: 'insert', items : [ 'Image','Flash','Table','HorizontalRule','Smiley','SpecialChar','PageBreak','Iframe' ] },
        '/',
        { name: 'styles', items : [ 'Styles','Format','Font','FontSize' ] },
        { name: 'colors', items : [ 'TextColor','BGColor' ] },
        { name: 'tools', items : [ 'Maximize', 'ShowBlocks','-','About' ] }
    ];

CKEDITOR.config.toolbar_Custom =
    [
        { name: 'basicstyles', items: ['Bold', 'Italic','Underline','Strike', '-','TextColor','BGColor'] },
        { name: 'formatting', items: ['NumberedList', 'BulletedList','-','Outdent','Indent', '-', 'Link', 'Unlink'] },
        { name: 'clipboard', items : [ 'Cut','Copy','Paste','PasteText','PasteFromWord' ] },
        { name: 'actions', items: ['Undo','Redo','-','RemoveFormat'] },
        { name: 'tools', items : [ 'Maximize' ] },
        '/',
        { name: 'styles', items : [ 'Styles','Format','Font','FontSize','Smiley' ] }
    ];