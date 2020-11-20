var viewer = new Viewer(document.getElementById('image'), {
    url: 'data-original',
    fullscreen: false,
    title: false,
    movable: false,
    toolbar: {
        zoomIn: {
            show: 1,
            size: 'large'
        },
        zoomOut: {
            show: 1,
            size: 'large'
        },
        oneToOne: {
            show: 1,
            size: 'large'
        },
        reset: {
            show: 1,
            size: 'large'
        },
        prev: {
            show: 1,
            size: 'large'
        },
        play: 1,
        next: {
            show: 1,
            size: 'large'
        },
        rotateLeft: {
            show: 1,
            size: 'large'
        },
        rotateRight: {
            show: 1,
            size: 'large'
        },
        flipHorizontal: {
            show: 1,
            size: 'large'
        },
        flipVertical: {
            show: 1,
            size: 'large'
        }
    }
});