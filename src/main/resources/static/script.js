async function consultOrder(){

    let order = (await fetch("/orders"));
    console.log(JSON.stringify(order));
}