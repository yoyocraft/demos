Array.prototype.print = function () {
    console.log(this.join('\n'));
}

const matrix = [
    [0, 2, 2, 0],
    [0, 0, 2, 2],
    [2, 4, 4, 2],
    [2, 4, 4, 4],
];

matrix.print();
console.log("----------");
move(matrix, 'up');
matrix.print();


/**
 * 根据指定方向移动矩阵中的元素
 * @param {number[][]} matrix - 二维矩阵
 * @param {string} direction - 移动方向，可以是 'up', 'down', 'left', 'right'
 */
function move(matrix, direction) {
    const rows = matrix.length;
    const cols = matrix[0].length;

    /**
     * 检查坐标 (i, j) 是否在矩阵范围内
     * @param {number} i - 行坐标
     * @param {number} j - 列坐标
     * @returns {boolean} - 如果在范围内返回 true，否则返回 false
     */
    function _inRange(i, j) {
        return i >= 0 && i < rows && j >= 0 && j < cols;
    }

    // 定义根据方向计算下一个坐标的函数
    const next = {
        up: (i, j) => [i + 1, j],
        down: (i, j) => [i - 1, j],
        left: (i, j) => [i, j + 1],
        right: (i, j) => [i, j - 1],
    }

    /**
     * 找到在指定方向上的下一个非零值的坐标
     * @param {number} i - 起始行坐标
     * @param {number} j - 起始列坐标
     * @returns {number[]} - 包含下一个非零值的坐标和值的数组，如果没有找到则返回 undefined
     */
    function _nextNonZeroValue(i, j) {
        if (!_inRange(i, j)) {
            return;
        }

        let [nextI, nextJ] = next[direction](i, j);
        while (_inRange(nextI, nextJ)) {
            if (matrix[nextI][nextJ] !== 0) {
                return [nextI, nextJ, matrix[nextI][nextJ]];
            }

            [nextI, nextJ] = next[direction](nextI, nextJ);
        }
    }


    /**
     * 执行计算以处理矩阵中的数值移动和合并
     * 根据给定的方向，寻找下一个非零值并尝试将其移动到当前位置或与当前位置的值合并
     *
     * @param {number} i - 当前行索引
     * @param {number} j - 当前列索引
     */
    function _cal(i, j) {
        if (!_inRange(i, j)) {
            return;
        }
        const result = _nextNonZeroValue(i, j);
        if (!result) {
            return;
        }
        const [nextI, nextJ, nextValue] = result;
        if (matrix[i][j] === 0) {
            matrix[i][j] = nextValue;
            matrix[nextI][nextJ] = 0;
            _cal(i, j);
        }
        else if (matrix[i][j] === nextValue) {
            matrix[i][j] *= 2;
            matrix[nextI][nextJ] = 0;
        }

        [i, j] = next[direction](i, j);
        _cal(i, j);
    }

    // 根据移动方向处理矩阵
    if (direction === 'up') {
        for (let j = 0; j < cols; j++) {
            _cal(0, j);
        }
    }
    else if (direction === 'down') {
        for (let j = 0; j < cols; j++) {
            _cal(rows - 1, j);
        }
    }
    else if (direction === 'left') {
        for (let i = 0; i < rows; i++) { }
    }
    else if (direction === 'right') {
        for (let i = 0; i < rows; i++) {
            _cal(i, cols - 1);
        }
    }
}